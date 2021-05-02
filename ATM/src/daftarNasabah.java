import java.util.*;
import java.io.*;

public class daftarNasabah {
	public static void main(String[] args) {
		Map<String,Rekening> collection = new HashMap<String,Rekening>();
		Rekening nasabahbac1 = new RekeningBAC("Aldi Setiawan","654321",1000000);
		Rekening nasabahbac2 = new RekeningBAC("Enggandi Yuda",500000);
		Rekening nasabahbac3 = new RekeningBAC("Asri Meli","111111");
		Rekening nasabahbin1 = new RekeningBIN("Rindho Ananta","222222",200000);
		Rekening nasabahbin2 = new RekeningBIN("Evodius Pr","333333",450000);
		Rekening nasabahbin3 = new RekeningBIN("Valdo Rendra","444444",90000);
		Rekening nasabahmarindi1 = new RekeningMarindi("Tiar K","555555",90000);
		Rekening nasabahmarindi2 = new RekeningMarindi("Benny A","666666");
		Rekening nasabahmarindi3 = new RekeningMarindi("Nobody Ever Be",90000);
		collection.put(nasabahbac1.getNoRek(), nasabahbac1);
		collection.put(nasabahbac2.getNoRek(), nasabahbac2);
		collection.put(nasabahbac3.getNoRek(), nasabahbac3);
		collection.put(nasabahbin1.getNoRek(), nasabahbin1);
		collection.put(nasabahbin2.getNoRek(), nasabahbin2);
		collection.put(nasabahbin3.getNoRek(), nasabahbin3);
		collection.put(nasabahmarindi1.getNoRek(), nasabahmarindi1);
		collection.put(nasabahmarindi2.getNoRek(), nasabahmarindi2);
		collection.put(nasabahmarindi3.getNoRek(), nasabahmarindi3);
		try {
			FileOutputStream fos = new FileOutputStream("database2");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(collection);
			oos.close();
			fos.close();
		}
		catch(IOException e) {
		}	
	}
}
