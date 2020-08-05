import org.junit.Assert.*
import org.junit.Test

class AuthorizationTest {

	@Test
	fun `initial state should be unauthorized and user id should be null`() {
		val authorization = AuthorizationPlayer()

		assertTrue(authorization.currentState() is State.Unauthorized)
		assertNull(authorization.currentState().userId)
	}

	@Test
	fun `when user was login should transition to authorized`() {
		val authorization = AuthorizationPlayer()

		authorization.login("user id")

		assertTrue(authorization.currentState() is State.Authorized)
		assertNotNull(authorization.currentState().userId)
		assertEquals("user id", authorization.currentState().userId)
	}

	@Test
	fun `when user was logout should transition to unauthorized`() {
		val authorization = AuthorizationPlayer()

		authorization.logout()

		assertTrue(authorization.currentState() is State.Unauthorized)
		assertNull(authorization.currentState().userId)
	}

}
