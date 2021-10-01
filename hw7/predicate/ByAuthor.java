package il.ac.tau.cs.software1.predicate;

public class ByAuthor implements Predicate<Book> {
	private char letter;

	public ByAuthor(char letter) { // Q2
		this.letter = letter;
	}

	@Override
	public boolean test(Book book) { // Q2
		String author = book.getAuthor().toLowerCase();
		return this.letter == author.charAt(0);
	}
}