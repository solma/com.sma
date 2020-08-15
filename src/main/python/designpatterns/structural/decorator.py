import os

class MediaPlayer(object):
    def play(self, media_path):
        _, file_format = os.path.splitext(media_path)
        print("Playing media file:\n\tname:", media_path, " format:", file_format)

# Decorator class adds some function to the decorated class.
class CloudBackupMediaPlayer(object):
    def __init__(self, media_player):
        self._media_player = media_player

    def play(self, media_path):
        print("Saving media file ", media_path, " to your Cloud.")
        self._media_player.play(media_path)

if __name__ == "__main__":
    media_path = 'test.avi'
    mp = MediaPlayer()
    cbmp = CloudBackupMediaPlayer(mp)
    cbmp.play(media_path)
