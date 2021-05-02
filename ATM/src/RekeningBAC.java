
public class RekeningBAC extends Rekening{

	protected RekeningBAC(String nama, String password, float saldo) {
		super(nama, password, saldo);
	}
	
	protected RekeningBAC(String nama, String password) {
		super(nama, password, 0);
	}
	
	protected RekeningBAC(String nama, float saldo) {
		super(nama,"123456", saldo);
	}

}
