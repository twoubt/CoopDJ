package emptyflash.coopdj

import org.scaloid.common._

import android.content.Intent
import android.util.Log

import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;


class CoopDJLoginActivity extends SActivity with PlayerNotificationCallback with ConnectionStateCallback {
    onCreate {
        lazy val builder = new AuthenticationRequest.Builder(SpotifySettings.CLIENT_ID, AuthenticationResponse.Type.TOKEN, SpotifySettings.REDIRECT_URL)
        builder setScopes Array[String]("streaming")
        lazy val request = builder.build()
        AuthenticationClient.openLoginActivity(this, SpotifySettings.REQUEST_CODE, request)
    }

    override def onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == SpotifySettings.REQUEST_CODE) {
            lazy val response = AuthenticationClient.getResponse(resultCode, intent)
            response.getType() match {
                case AuthenticationResponse.Type.TOKEN => {
                    lazy val config = new Config(this, response.getAccessToken(), SpotifySettings.CLIENT_ID)
                    config.useCache(false)
                    val spotifyPlayer: Player = Spotify.getPlayer(config, this, new Player.InitializationObserver() {
                        override def onInitialized(player: Player) {
                            player.addConnectionStateCallback(CoopDJLoginActivity.this)
                            player.addPlayerNotificationCallback(CoopDJLoginActivity.this)
                            player.play("spotify:track:2TpxZ7JUBn3uw46aR7qd6V")
                        }

                        override def onError(thorwable: Throwable) {

                        }
                    })
                }
                case _ => Log.d("LoginActivity", "Response type didn't match TOKEN")
            }
        }
    }

    override def onLoggedIn() {

    }

    override def onLoggedOut() {

    }

    override def onLoginFailed(error: Throwable) {

    }

    override def onTemporaryError() {

    }

    override def onConnectionMessage(message: String) {

    }

    override def onPlaybackEvent(eventType: PlayerNotificationCallback.EventType, playerState: PlayerState) {

    }

    override def onPlaybackError(errorType: PlayerNotificationCallback.ErrorType, errorDetails: String) {

    }

    onDestroy {
        Spotify.destroyPlayer(this)
        super.onDestroy()
    }

}
