/**
 * Programming Assignment 4
 *
 * @author Natalie Young
 * @since 2021-10-26
 */

import tester.*;

interface Tweet
{
	public boolean isReplyTo(Tweet other);
	public int totalLikes();
	public String allAuthors();
	public boolean textAppearsOnThread(String text);
}

class User
{
	String userName; 
	String displayName;		// also called full name
	int numberOfFollowers;

	// Constructor takes a value for each field and initializes it
	User(String userName, String displayName, int numberOfFollowers)
	{
		this.userName = userName;
		this.displayName = displayName;
		this.numberOfFollowers = numberOfFollowers;
	}
	
	/**
	 * Returns string that contains fullname followed by space and
	 * at-symbol before username
	 *
	 * @return text
	 */
	String toText()
	{
		String text = displayName + " @" + userName;

		return text;
	}
}

class TextTweet implements Tweet
{
	User author;
	String contents;
	int likes;

	TextTweet(User author, String contents, int likes)
	{
		this.author = author;
		this.contents = contents;
		this.likes = likes;
	}

	/**
	 * Returns false no matter what Tweet it receives
	 *
	 * @param other
	 */
	public boolean isReplyTo(Tweet other)
	{
		return false;
	}

	/**
	 * Returns number of likes on this TextTweet object
	 *
	 * @return likes
	 */
	public int totalLikes()
	{
		return this.likes;
	}

	/**
	 * Returns username of author of this TextTweet
	 *
	 * @return this.author.userName
	 */
	public String allAuthors()
	{
		return this.author.userName;
	}

	/**
	 * Returns true when given text is in contents of this TextTweet;
	 * false otherwise
	 *
	 * @param text
	 * @return boolean
	 */
	public boolean textAppearsOnThread(String text)
	{
		return this.contents.contains(text);
	}
}

class ReplyTweet implements Tweet
{
	User author;
	String contents;
	int likes;
	Tweet replyTo;

	ReplyTweet(User author, String contents, int likes, Tweet replyTo)
	{
		this.contents = contents;
		this.likes = likes;
		this.author = author;
		this.replyTo = replyTo;
	}

	/**
	 * Returns true if replyTo of this ReplyTweet is the given Tweet
	 * as compared by ==
	 */
	public boolean isReplyTo(Tweet other)
	{
		return this.replyTo == other;
	}

	/**
	 * Returns total number of likes on this ReplyTweet object PLUS
	 * totalLikes of its replyTo Tweet; thread that is 4 replies long
	 * should sum likes on all 4 tweets
	 *
	 * @return
	 */
	public int totalLikes()
	{
		int totalLikes = this.likes + this.replyTo.totalLikes();

		return totalLikes;
	}

	/**
	 * Returns username of author of this ReplyTweet followed by
	 * semicolon, then by allAuthors of its replyTo Tweet
	 *
	 */
	public String allAuthors()
	{
		return this.author.userName + ";" + replyTo.allAuthors();
	}

	/**
	 * Returns true when given text appears in contents of this
	 * ReplyTweet or if it appears on thread of replyTo Tweet
	 */
	public boolean textAppearsOnThread(String text)
	{
		return (this.contents.contains(text)
				|| this.replyTo.textAppearsOnThread(text));
	}
}

class TweetsExample
{
	User joe = new User("joepolitz", "Joe Gibbs Politz", 999);
	User greg = new User("gregory_miranda", "Greg Miranda", 9999);
	User rachel = new User("Rachel__Lim", "Rachel Lim", 1000000);
	Tweet t1 = new TextTweet(this.joe, "Java 17 has a cool feature called records", 77);
	Tweet t2 = new ReplyTweet(this.greg, "Hmm I wonder if we could use it for CSE11", 12, this.t1);
	Tweet t3 = new ReplyTweet(this.greg, "Thought about this more, probably not yet, too new.", 73, this.t2);
	Tweet t4 = new ReplyTweet(this.joe, "Yeah, good point. Maybe in 2022.", 10, this.t3);
	Tweet t5 = new ReplyTweet(this.rachel, "Yeah... I don't want to rewrite the book right this minute", 1005, this.t2);

	void testIsReplyTo(Tester t) {
		t.checkExpect(this.t1.isReplyTo(this.t2), false);
		t.checkExpect(this.t2.isReplyTo(this.t1), true);
		t.checkExpect(this.t5.isReplyTo(this.t2), true);
		t.checkExpect(this.t2.isReplyTo(this.t2), false);
		t.checkExpect(this.t4.isReplyTo(this.t3), true);
	}

	void testTotalLikes(Tester t) {
		t.checkExpect(this.t5.totalLikes(), 1005 + 12 + 77);
		t.checkExpect(this.t4.totalLikes(), 10 + 73 + 12 + 77);
		t.checkExpect(this.t1.totalLikes(), 77);
	}

	void testAllAuthors(Tester t) {
		t.checkExpect(this.t1.allAuthors(), "joepolitz");
		t.checkExpect(this.t2.allAuthors(), "gregory_miranda;joepolitz");
		t.checkExpect(this.t3.allAuthors(), "gregory_miranda;gregory_miranda;joepolitz");
		t.checkExpect(this.t5.allAuthors(), "Rachel__Lim;gregory_miranda;joepolitz");
	}

	void testTextAppearsOnThread(Tester t) {
		t.checkExpect(this.t1.textAppearsOnThread("joepolitz"), false);
		t.checkExpect(this.t1.textAppearsOnThread("2022"), false);
		t.checkExpect(this.t1.textAppearsOnThread("cool"), true);
		t.checkExpect(this.t4.textAppearsOnThread("wonder"), true);
		t.checkExpect(this.t4.textAppearsOnThread("Java"), true);
		t.checkExpect(this.t4.textAppearsOnThread("rewrite"), false);
		t.checkExpect(this.t4.textAppearsOnThread("2022"), true);
	}
}
