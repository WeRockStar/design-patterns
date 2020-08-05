import org.junit.Assert.*
import org.junit.Test

class MusicPlayerTest {

	@Test
	fun `initial state should be stopped state`() {
		val player = MusicPlayer()

		assertTrue(player.currentState() is MusicState.Stopped)
		assertNull(player.currentMusic())
	}

	@Test
	fun `when user press a play button should transition to Playing state`() {
		val player = MusicPlayer()
		val musicName = "ไส่ว่าสิบ่ถิ่มกัน"

		player.play(musicName)

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(musicName, player.currentMusic())
	}

	@Test
	fun `while music is playing, when user press a pause button should transition to Pause state`() {
		val musicName = "Don't care"
		val player = MusicPlayer(MusicState.Playing(musicName))

		player.pause()

		assertTrue(player.currentState() is MusicState.Pause)
		assertEquals(musicName, player.currentMusic())
	}

	@Test
	fun `while music is playing, when user press a stop button should transition to Stop state`() {
		val musicName = "Don't care"
		val player = MusicPlayer(MusicState.Playing(musicName))

		player.stop()

		assertTrue(player.currentState() is MusicState.Stopped)
		assertEquals(musicName, player.currentMusic())
		assertEquals(0, player.currentState().progress)
	}

	@Test
	fun `while music is paused, when user press a play button should transition to Playing state`() {
		val musicName = "Don't care"
		val currentProgress = 10
		val player = MusicPlayer(MusicState.Pause(musicName, currentProgress))

		player.play(musicName)

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(currentProgress, player.currentState().progress)
		assertEquals(musicName, player.currentMusic())
	}

	@Test
	fun `while music is playing, when user press a next button should transition to next music`() {
		val currentMusicName = "Don't care"
		val currentProgress = 10
		val player = MusicPlayer(MusicState.Playing(currentMusicName, currentProgress))

		player.next()

		val expectNextMusic = "ไส่ว่าสิบ่ถิ่มกัน"
		val expectStartAt = 0

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(expectStartAt, player.currentState().progress)
		assertEquals(expectNextMusic, player.currentMusic())
	}

	@Test
	fun `while music is playing at the last music, when user press a next button should transition to the first music in the list`() {
		val currentMusicName = "Don't care"
		val currentProgress = 10
		val player = MusicPlayer(MusicState.Playing(currentMusicName, currentProgress))

		player.next()
		player.next()
		player.next()
		player.next()
		player.next()

		val expectFirstMusic = "Don't care"
		val expectStartAt = 0

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(expectStartAt, player.currentState().progress)
		assertEquals(expectFirstMusic, player.currentMusic())
	}

	@Test
	fun `while music is playing, when user press a previous button should transition to previous music`() {
		val currentMusicName = "ไส่ว่าสิบ่ถิ่มกัน"
		val currentProgress = 10
		val player = MusicPlayer(MusicState.Playing(currentMusicName, currentProgress))

		player.previous()

		val expectNextMusic = "Don't care"
		val expectStartAt = 0

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(expectStartAt, player.currentState().progress)
		assertEquals(expectNextMusic, player.currentMusic())
	}

	@Test
	fun `while music is playing at first music, when user press a previous button should transition to last music`() {
		val currentMusicName = "Don't care"
		val currentProgress = 10
		val player = MusicPlayer(MusicState.Playing(currentMusicName, currentProgress))

		player.previous()

		val expectNextMusic = "วิบวับ"
		val expectStartAt = 0

		assertTrue(player.currentState() is MusicState.Playing)
		assertEquals(expectStartAt, player.currentState().progress)
		assertEquals(expectNextMusic, player.currentMusic())
	}
}
