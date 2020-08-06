import org.junit.Assert.*
import org.junit.Test

class AuthorizationTest {

	@Test
	fun `initial state should be unauthorized and user id should be null`() {
		val authorization = AuthorizationPlayer()

		assertFalse(authorization.isAuthorized)
		assertTrue(authorization.currentState is State.Unauthorized)
		assertNull(authorization.currentState.userId)
	}

	@Test
	fun `when user was login should transition to authorized`() {
		val authorization = AuthorizationPlayer()

		authorization.login("user id")

		assertTrue(authorization.currentState is State.Authorized)
		assertTrue(authorization.isAuthorized)
		assertNotNull(authorization.currentState.userId)
		assertEquals("user id", authorization.currentState.userId)
	}

	@Test
	fun `when user was logout should transition to unauthorized`() {
		val authorization = AuthorizationPlayer()

		authorization.logout()

		assertFalse(authorization.isAuthorized)
		assertTrue(authorization.currentState is State.Unauthorized)
		assertNull(authorization.currentState.userId)
	}

}
