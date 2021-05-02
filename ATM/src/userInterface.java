import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class userInterface {
	
	private static Scanner scanner = new Scanner(System.in);
	
	protected static boolean looping(char bool) {
		return bool == 'Y' ? true : false;
	}
	
	protected static void menu(Rekening rekening) {
		System.out.println("Selamat Datang sdr/i " + rekening.getNama());
		System.out.println(".\n.\n.");
		System.out.println("1. Info Rekening");
		System.out.println("2. Transfer Uang");
		System.out.println("3. Setor Tunai");
		System.out.println("4. Tarik Tunai");
		System.out.println("5. Ganti Password");
		System.out.println("6. Beli Pulsa");
		System.out.println("7. Keluar");
		System.out.print("\nMasukkan Pilihan Anda : ");
	}
	
	protected static void gantiPassword(Rekening rekening) {
		scanner = new Scanner(System.in);
		System.out.print("Masukkan Password Baru : ");
		String passwordbaru = scanner.nextLine();
		if( passwordbaru.matches("[0-9]*$")) {
			rekening.setPassword(passwordbaru);
			System.out.println("Password Telah Diganti");
		}else {
			System.out.println("Password Tidak Sesuai");
		}
	}
	
	protected static void tarikTunai(Rekening rekening) {
		System.out.print("Masukan Uang yang Akan DiTarik : ");
		float saldo = scanner.nextFloat();
		rekening.tarik(saldo);
	}
	
	protected static void setorTunai(Rekening rekening) {
		System.out.print("Masukan Uang yang Akan DiSetor : ");
		float saldo = scanner.nextFloat();
		rekening.setor(saldo);
	}
	
	protected static void transferTunai(Rekening asal,Rekening tujuan) {
		System.out.println("Nama Penerima : " + tujuan.getNama());
		System.out.print("Masukan Saldo yang Akan Ditransfer : ");
		float saldo = scanner.nextFloat();
		asal.transfer(saldo, tujuan);
	}	
	
	protected static void infoRekening(Rekening rekening) {
		System.out.println("\nNama\t\t:" + rekening.getNama());
		System.out.println("Saldo\t\t:" + rekening.getSaldo() );
		System.out.println("Nomor Rekening\t:" + rekening.getNoRek() );
		System.out.println("Status Blokir\t:" + rekening.getStatusBlokir() );
	}
	
	protected static boolean getStatus() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nIngin Transaksi Lagi (Y/N) : ");
		char ces = 0;
		try {
			ces = (char) reader.read();
		} catch (IOException e) {
		}
	return ces == 'Y' ? true : false;
	}
	
	protected static void beliPulsa(float saldo,Rekening rekening) {
		rekening.beliPulsa(saldo);
	}
	
}