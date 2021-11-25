/**
 * Programming Assignment 4
 *
 * @author Natalie Young
 * @since 2021-10-26
 */

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
	String contents;
	int likes;
	User author;

	TextTweet(String contents, int likes, User author)
	{
		this.contents = contents;
		this.likes = likes;
		this.author = author;
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
	String contents;
	int likes;
	User author;
	Tweet replyTo;

	/**
	 * Returns true if replyTo of this ReplyTweet is the given Tweet
	 * as compared by ==
	 */
	public boolean isReplyTo(Tweet other)
	{
		return replyTo == other;
	}

	/**
	 * Returns total number of likes on this ReplyTweet object PLUS
	 * totalLikes of its replyTo Tweet
	 *
	 * @return totalLikes;
	 */
	public int totalLikes()
	{}

	/**
	 * Returns username of author of this ReplyTweet followed by
	 * semicolon, then by allAuthors of its replyTo Tweet
	 *
	 * @return 
	 */
	public String allAuthors()
	{}

	/**
	 * Returns true when given text appears in contents of this
	 * ReplyTweet or if it appears on thread of replyTo Tweet
	 */
	public boolean textAppearsOnThread(String contents)
	{}
}
