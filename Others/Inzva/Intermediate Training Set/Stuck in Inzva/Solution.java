/**
 * Problem Link - https://www.hackerrank.com/contests/inzva-intermediate-training-set/challenges/stuck-in-inzva
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		Solver solver = new Solver();
		solver.solve(in, out);
		out.close();
	}

	static class Solver {

		public void solve(InputReader in, PrintWriter out) {
			String strBook = in.next();
			String keyword = in.next();

			String clearStrBook = processString(strBook);
			String clearKeyword = processString(keyword);
			
			out.println(clearStrBook.contains(clearKeyword) ? "yes" : "no");
		}

		private String processString(String str) {
			StringBuilder clearStr = new StringBuilder();
			char lastChar = '\0';
			for (int i = 0; i < str.length(); ++i) {
				char c = str.charAt(i);
				if (c != lastChar || !isVowel(c)) clearStr.append(c);
				lastChar = c;
			}
			return clearStr.toString();
		}

		private boolean isVowel(char c) {
			return c == 'a' || c == 'e' || c == 'o' || c == 'i' || c == 'u';
		}
	}

	static class InputReader {

		private InputStream stream;

		private byte[] buf = new byte[1024];

		private int curChar;

		private int numChars;

		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		private int read() {
			if (numChars == -1)
				throw new InputMismatchException();

			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				}
				catch (IOException e) {
					throw new InputMismatchException();
				}

				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public int nextInt() {
			int c = read();

			while (isSpaceChar(c))
				c = read();

			int sgn = 1;

			if (c == '-') {
				sgn = -1;
				c = read();
			}

			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));

			return res * sgn;
		}

		public long nextLong() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;

			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			}
			while (!isSpaceChar(c));
			return res * sgn;
		}

		private String readString() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			}
			while (!isSpaceChar(c));

			return res.toString();
		}

		public boolean isSpaceChar(int c) {
			if (filter != null)
				return filter.isSpaceChar(c);
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {

			boolean isSpaceChar(int ch);

		}
	}
}
