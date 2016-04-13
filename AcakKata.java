import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AcakKata {
	private static final String ANS_TRUE = "BENAR!";
	private static final String ANS_FALSE = "SALAH! Silahkan coba lagi!";
	private static final String QUERY = "Jawab: ";
	private static final String TEBAK = "TEBAK kata: ";
	private static final String EXIT = "EXIT";
	private static final String INPUT = "INPUT";
	private static final String MAIN = "MAIN";
	private ArrayList<String> listKata;
	BufferedReader in = new BufferedReader(new InputStreamReader (System.in));

	public AcakKata() {
		listKata = new ArrayList<String>();
		listKata.add("buku");
		listKata.add("kucing");
		listKata.add("pusheen");
	}

	public void addKata(String kata) {
		listKata.add(kata);
	}
	
	public String getKata(int i) {
		return listKata.get(i);
	}

	public int genRandom() {
		Random seed = new Random();
		int i =  seed.nextInt(listKata.size());
		return i;
	}

	public String randomKata(String init) {
		StringBuilder build = new StringBuilder();
		String tmp = init;

		while (tmp.equals(init)) {
			boolean flag[] = new boolean[init.length()];
	
			int i = genRandom();

			for (int j=0; j<init.length(); j++) {
				if(!flag[i]) {
					build.append(init.charAt(i));
					flag[i] = true;
				}
				i = genRandom();
			}

			for(int j =0; j<init.length(); j++)
				if(!flag[j]) build.append(init.charAt(j));
		
			tmp = build.toString();
		}
		return tmp;
	}

	public void getOption() {
		String[] choices = {"Masukkan pilihan", "===================================", "", "Ketik [INPUT] untuk memasukkan kata", "Ketik [MAIN] untuk bermain", "Ketik [EXIT] untuk keluar"};
		for(int i =0; i<choices.length; i++)
			System.out.println(choices[i]);
		System.out.println();
	}

	public void startANewGame() throws IOException{
		String init = getKata(genRandom());
		String curr = randomKata(init);
		System.out.println(TEBAK + curr);
		System.out.println(QUERY);
		String ans = in.readLine();
		while (!(ans).equalsIgnoreCase(init)) {
			System.out.println(ANS_FALSE);
			System.out.println(QUERY);
			ans = in.readLine();
		}
		System.out.println(ANS_TRUE);
	}

	public void play() throws IOException {
		getOption();
		String choice = in.readLine();
		while(true) {
			if(choice.equalsIgnoreCase(MAIN)) {
				startANewGame();
			} else if(choice.equalsIgnoreCase(INPUT)) {
				System.out.println("Silahkan masukkan kata baru");
				String newKata = in.readLine();
				addKata(newKata);
			} else if(choice.equalsIgnoreCase(EXIT)) {
				System.exit(0);
			} else {
				System.out.println("Maaf silahkan masukkan pilihan Anda!");
			}
			System.out.println();
			getOption();
			choice=in.readLine();
		}
		
	}

	public static void main(String args[]) throws IOException {
		AcakKata hm = new AcakKata();
		hm.play();
	}
}
