
public class RekeningBIN extends Rekening{
	
	protected RekeningBIN(String nama, String password, float saldo) {
		super(nama, password, saldo);
	}
	
	protected RekeningBIN(String nama, String password) {
		super(nama, password, 0);
	}
	
	protected RekeningBIN(String nama, float saldo) {
		super(nama,"123456", saldo);
	}
	
}
