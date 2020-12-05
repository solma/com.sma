import os


class MediaPlayerImp(object):
  def play(self, media_path) -> None:
    _, file_format = os.path.splitext(media_path)
    print("Playing media file:\n\tname:", media_path, " format:", file_format)


# Bridge pattern separates the interface class and the implementation class.
class MediaPlayer(object):
  def __init__(self, media_player_imp: MediaPlayerImp):
    self._media_player_imp = media_player_imp

  def play(self, media_path: str) -> None:
    self._media_player_imp.play(media_path)


if __name__ == "__main__":
  media_path = 'test.avi'
  mpi = MediaPlayerImp()
  mp = MediaPlayer(mpi)
  mp.play(media_path)
