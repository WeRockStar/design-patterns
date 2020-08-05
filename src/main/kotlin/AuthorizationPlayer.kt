sealed class State {
	abstract val userId: String?

	class Authorized(private val id: String) : State() {
		override val userId: String? get() = id
	}
	object Unauthorized : State() {
		override val userId: String? = null
	}
}

class AuthorizationPlayer(state: State = State.Unauthorized) {

	private var _state: State = state

	fun currentState(): State = _state

	fun login(userId: String) {
		_state = State.Authorized(userId)
	}

	fun logout() {
		_state = State.Unauthorized
	}

}
