import os

class MediaPlayer(object):
    def play(self, media_path, file_format):
        print("Playing media file:\n\tname:", media_path, " format:", file_format)

# Adaptor class changes the interface of the adaptee class.
class SmartMediaPlayer(object):
    def __init__(self, media_player):
        self._media_player = media_player

    def play(self, media_path):
        print("Playing with SmartMediaPlayer")
        _, file_format = os.path.splitext(media_path)
        self._media_player.play(media_path, file_format)

if __name__ == "__main__":
    media_path = 'test.avi'
    mp = MediaPlayer()
    smp = SmartMediaPlayer(mp)
    # mp.play(media_path, 'avi')  # adaptee client call
    smp.play(media_path)  # adaptor client call
