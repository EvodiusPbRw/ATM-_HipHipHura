
public class RekeningMarindi extends Rekening{
	
	protected RekeningMarindi(String nama, String password, float saldo) {
		super(nama, password, saldo);
	}
	
	protected RekeningMarindi(String nama, String password) {
		super(nama, password, 0);
	}
	
	protected RekeningMarindi(String nama, float saldo) {
		super(nama,"123456", saldo);
	
	}
}