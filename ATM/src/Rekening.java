import java.io.*;

public abstract class Rekening implements Serializable,Pulsa{
	private static final String Rekening = null;
	private String nama;
	private String password;
	private static int noRek = 1 ;
	private String noRekening;
	private float saldo = 0 ;
	private String jenis;
	private boolean statusBlokir = false;
	
	protected Rekening(String nama,String password, float saldo) {
		this.nama = nama;
		this.setPassword(password);
		this.saldo = saldo;
		this.noRekening = String.valueOf(noRek);
		noRek++;
	}
	
	protected Rekening(String nama,String password) {
		this(nama,password,0);noRek++;
	}
	
	protected Rekening(String nama, float saldo) {
		this(nama,"123456",saldo);noRek++;
	}
	
	protected void transfer(float saldo,Rekening tujuan) {
		if(saldo>=0.0 && this.saldo-saldo >= 0.0 ) {
			this.tarik(saldo);
			tujuan.setor(saldo);
			System.out.println("Transaksi Berhasil\n");
		}else {
			System.out.println("Saldo Tidak Cukup");
		}
		
	}
	
	protected void tarik(float saldo) {
		if(saldo>=0.0 && this.saldo-saldo >= 0.0 ) {//saldo>=0.0 && this.saldo-saldo >= 0.0 
			this.saldo -=saldo;
		}else {
			System.out.println("Invalid Saldo");
		}
	}
	
	protected void setor(float saldo) {
		if(saldo>0) {
			this.saldo +=saldo;
		}else {
			System.out.println("Invalid Saldo");
		}
	}
	
	protected boolean getStatusBlokir() {
		return this.statusBlokir;
	}
	
	protected void setStatusBlokir(boolean status) {
		this.statusBlokir = status;
	}

	protected String getNama() {
		return nama;
	}

	protected float getSaldo() {
		return saldo;
	}
	
	protected String getPassword() {
		return password;
	}
	
	protected void setPassword(String password) {
		this.password = password;
	}
	
	protected String getNoRek() {
		return this.noRekening;
	}
	
	public void beliPulsa(float saldo) {
		if( saldo % 50000 == 0) {
			this.tarik(saldo);
			System.out.println("Pulsa telah dibeli sebesar Rp. " + saldo);
		}else {
			System.out.println("Pulsa gagal dibeli");
		}

	}
	
}
