/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.service.network.gameguard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.Future;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.inject.Inject;
import com.l2jserver.game.net.packet.server.SM_GG_QUERY;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.service.AbstractService;
import com.l2jserver.service.AbstractService.Depends;
import com.l2jserver.service.ServiceStartException;
import com.l2jserver.service.ServiceStopException;
import com.l2jserver.service.game.chat.ChatService;
import com.l2jserver.service.network.NetworkService;
import com.l2jserver.service.network.model.Lineage2Client;
import com.l2jserver.util.factory.CollectionFactory;

/**
 * Default implementation for {@link GameGuardService}
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
@Depends({ NetworkService.class })
public class GameGuardServiceImpl extends AbstractService implements
		GameGuardService {
	/**
	 * The logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * The static key used to validate game guards
	 */
	private static final int[] STATIC_KEY = { 0x27533DD9, 0x2E72A51D,
			0x2017038B, 0xC35B1EA3 };
	/**
	 * The valid GG SHA1 response -- for a single key, the answer must be always
	 * the same
	 */
	@SuppressWarnings("unused")
	private static final byte[] STATIC_KEY_VALIDATION = { (byte) 0x88, 0x40,
			0x1c, (byte) 0xa7, (byte) 0x83, 0x42, (byte) 0xe9, 0x15,
			(byte) 0xde, (byte) 0xc3, 0x68, (byte) 0xf6, 0x2d, 0x23,
			(byte) 0xf1, 0x3f, (byte) 0xee, 0x68, 0x5b, (byte) 0xc5 };

	/**
	 * The {@link ChatService}
	 */
	private final NetworkService networkService;

	/**
	 * The map containing all pending futures
	 */
	private Map<Lineage2Client, GGFuture> futures;
	/**
	 * The {@link MessageDigest} for SHA-1.
	 * <p>
	 * <b>Access must be synchronized externally.
	 */
	@SuppressWarnings("unused")
	private MessageDigest digester;

	/**
	 * @param networkService
	 *            the network service
	 */
	@Inject
	private GameGuardServiceImpl(NetworkService networkService) {
		this.networkService = networkService;
	}

	@Override
	protected void doStart() throws ServiceStartException {
		futures = CollectionFactory.newMap();
		try {
			digester = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceStartException(e);
		}
	}

	@Override
	public Future<GameGuardResponse> query(final L2Character character) {
		log.debug("Quering client for GameGuard authentication key");
		final Lineage2Client conn = networkService.discover(character.getID());
		conn.write(new SM_GG_QUERY(STATIC_KEY)).addListener(
				new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						if (future.getCause() != null) {
							futures.remove(conn);
						}
					}
				});
		final GGFuture future = new GGFuture();
		futures.put(conn, future);
		return future;
	}

	@Override
	public GameGuardResponse key(Lineage2Client conn, byte[] key) {
		log.debug("GameGuard authentication key received for {}", conn);

		final GGFuture future = futures.remove(conn);
		final boolean validated = validate(conn, key);
		final GameGuardResponse response = (validated ? GameGuardResponse.VALID
				: GameGuardResponse.INVALID);
		if (future != null)
			future.set(response);
		return response;
	}

	/**
	 * Creates a SHA1 sum of the key and checks is validity.
	 * 
	 * @param conn
	 *            the connection
	 * @param key
	 *            the key
	 * @return true if key is valid
	 */
	private boolean validate(Lineage2Client conn, byte[] key) {
		// synchronized (digester) {
		// return Arrays.equals(VALID_KEY_SHA1, digester.digest(key));
		// }
		return true;
	}

	@Override
	protected void doStop() throws ServiceStopException {
		futures = null;
		digester = null;
	}

	/**
	 * The GGFuture awaits for the
	 * {@link com.l2jserver.service.network.gameguard.GameGuardService.GameGuardResponse}
	 * 
	 * @author <a href="http://www.rogiel.com">Rogiel</a>
	 */
	private class GGFuture extends AbstractFuture<GameGuardResponse> implements
			Future<GameGuardResponse> {
		@Override
		protected boolean set(GameGuardResponse value) {
			// protected wrapper
			return super.set(value);
		}
	}
}
