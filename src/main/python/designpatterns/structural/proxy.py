import os


class MediaPlayer(object):
  def play(self, media_path: str) -> None:
    _, file_format = os.path.splitext(media_path)
    print("Playing media file:\n\tname:", media_path, " format:", file_format)


# Proxy class provides access control for the original class.
class MediaPlayerProxy(object):
  def __init__(self, media_player: MediaPlayer):
    self._media_player = media_player

  def play(self, media_path: str) -> None:
    if 'porn' in media_path:
      print("Cannot play porn file: ", media_path)
      return
    self._media_player.play(media_path)


if __name__ == "__main__":
  media_path = 'porn.avi'
  mp = MediaPlayer()
  mpp = MediaPlayerProxy(mp)
  mpp.play(media_path)
