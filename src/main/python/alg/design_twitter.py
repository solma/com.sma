"""
Design a simplified version of Twitter where users can post tweets,
follow/unfollow another user and is able to see the 10 most recent tweets
in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news
feed. Each item in the news feed must be posted by users who the user followed
or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.

Example:
Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);
// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);
// User 1 follows user 2.
twitter.follow(1, 2);
// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);
// User 1 unfollows user 2.
twitter.unfollow(1, 2);
// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
"""

from collections import defaultdict

class Twitter(object):

  def __init__(self):
    """
    Initialize your data structure here.
    """
    self._follower_map = defaultdict(set)
    self._followee_map = defaultdict(set)
    self._global_news_feed = []
    self._news_feed_map = defaultdict(list)  # userid -> [tweet_idx_in_global_news_feed]

  def postTweet(self, userId, tweetId):
    """
    Compose a new tweet.
    :type userId: int
    :type tweetId: int
    :rtype: void
    """
    self._global_news_feed.append(tweetId)
    self._news_feed_map[userId] = [len(self._global_news_feed) - 1] + self._news_feed_map[userId]

  def getNewsFeed(self, userId):
    """
    Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
    :type userId: int
    :rtype: List[int]
    """
    import heapq
    heap = []
    users = list(self._followee_map[userId]) + [userId]
    for user in users:
      if self._news_feed_map[user]:
        heapq.heappush(heap, (-self._news_feed_map[user][0], user, 0))

    ret = []
    while len(ret) < 10 and len(heap) > 0:
      latest_tweet_idx, user, idx = heapq.heappop(heap)
      ret.append(self._global_news_feed[-latest_tweet_idx])
      if idx < len(self._news_feed_map[user]) - 1:
        heapq.heappush(heap, (-self._news_feed_map[user][idx + 1], user, idx + 1))
    return ret

  def follow(self, followerId, followeeId):
    """
    Follower follows a followee. If the operation is invalid, it should be a no-op.
    :type followerId: int
    :type followeeId: int
    :rtype: void
    """
    if followerId == followeeId:
      return
    self._followee_map[followerId].add(followeeId)
    self._follower_map[followeeId].add(followerId)


  def unfollow(self, followerId, followeeId):
    """
    Follower unfollows a followee. If the operation is invalid, it should be a no-op.
    :type followerId: int
    :type followeeId: int
    :rtype: void
    """
    if followerId == followeeId:
      return
    if followeeId in self._followee_map[followerId]:
      self._followee_map[followerId].remove(followeeId)
    if followerId in self._follower_map[followeeId]:
      self._follower_map[followeeId].remove(followerId)

def test_case1():
  twitter = Twitter()
  twitter.postTweet(1, 1)
  print twitter.getNewsFeed(1)
  twitter.follow(2, 1)
  print twitter.getNewsFeed(2)

def test_case2():
  twitter = Twitter()
  twitter.postTweet(1, 5)
  print twitter.getNewsFeed(1)
  twitter.follow(1, 2)
  twitter.postTweet(2, 6)

  print twitter.getNewsFeed(1)
  twitter.unfollow(1, 2)
  print twitter.getNewsFeed(1)

test_case2()