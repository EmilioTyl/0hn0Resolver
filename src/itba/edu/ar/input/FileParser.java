package itba.edu.ar.input;

import java.awt.Point;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import itba.edu.ar.model.Token;
import itba.edu.ar.model.TokenFactory;

public class FileParser {

	private static Pattern newLinePattern = Pattern.compile("\\n");
	private static Pattern spacesPattern = Pattern.compile("\\s+");
	private static TokenFactory tokenFactory = TokenFactory.getInstance();
	private int boardX;
	private int boardY;
	private boolean firstLine = true;
	private Map<Point, Token> tokens;

	public int getBoardX() {
		return boardX;
	}

	public int getBoardY() {
		return boardY;
	}

	public Map<Point, Token> getTokens() {
		return tokens;
	}

	public void parseFile(String pathString) throws IOException {
		tokens = new HashMap<Point, Token>();
		Path path = Paths.get(pathString);
		Scanner scanner = new Scanner(path);
		scanner.useDelimiter(newLinePattern);

		while (scanner.hasNext()) {
			String line = scanner.next();
			if (firstLine) {
				parseDimensions(line);
				firstLine = false;

			} else {
				parseToken(line);
			}

		}

		scanner.close();
	}

	private void parseDimensions(String line) {
		Scanner lineScanner = getLineScanner(line);
		boardX = lineScanner.nextInt();
		boardY = lineScanner.nextInt();

	}

	private void parseToken(String line) {
		Scanner lineScanner = getLineScanner(line);
		int x = lineScanner.nextInt();
		int y = lineScanner.nextInt();
		Token token = getToken(lineScanner.next());
		tokens.put(new Point(x, y), token);
	}

	private static Token getToken(String next) {

		switch (next) {
		case "W":
			return tokenFactory.getWall();
		default:
			return tokenFactory.getNumber(Integer.parseInt(next));
		}
	}

	private static Scanner getLineScanner(String line) {
		Scanner scanner = new Scanner(line);
		scanner.useLocale(Locale.US);
		scanner.useDelimiter(spacesPattern);
		return scanner;
	}
}
