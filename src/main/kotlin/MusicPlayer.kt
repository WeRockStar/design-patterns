sealed class MusicState {
	abstract val musicName: String?
	open val progress: Int = -1

	class Playing(private val name: String, private val currentProgress: Int = 0): MusicState() {
		override val musicName: String? get() = name
		override val progress: Int get() = currentProgress
	}

	class Pause(private val name: String, private val currentProgress: Int = 0) : MusicState() {
		override val musicName: String? get() = name
		override val progress: Int get() = currentProgress
	}

	class Stopped(private val name: String? = null) : MusicState() {
		override val musicName: String? get() = name
		override val progress: Int get() = 0
	}
}

class MusicPlayer(state: MusicState = MusicState.Stopped()) {

	private val musics = listOfNotNull("Don't care", "ไส่ว่าสิบ่ถิ่มกัน", "หมอก", "วาฬเกยตื้น", "วิบวับ")
	private var _state: MusicState = state

	val currentState: MusicState get() = _state

	fun play(name: String, progress: Int = _state.progress) {
		_state = MusicState.Playing(findMusic(name), progress)
	}

	fun pause() {
		_state = MusicState.Pause(findMusic())
	}

	fun stop() {
		_state = MusicState.Stopped(findMusic())
	}

	fun next() {
		val musicIndex = index.inc()
		val musicName = findMusicBy(musicIndex) { musics.first() }
		play(musicName, 0)
	}

	fun previous() {
		val musicIndex = index.dec()
		val musicName = findMusicBy(musicIndex) { musics.last() }
		play(musicName, 0)
	}

	val currentMusic: String? get() = _state.musicName

	private fun findMusic(name: String = currentMusic.orEmpty()) = musics.firstOrNull { it == name }.orEmpty()
	private fun findMusicBy(index: Int, func: () -> String) = musics.getOrElse(index) { func() }
	private val index = musics.indexOf(_state.musicName)
}
