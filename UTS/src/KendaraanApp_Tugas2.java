// Tugas 2: Penggunaan Interface dan Abstract Class

// Judul: 
// Simulasi Kendaraan dan Perilakunya

// Deskripsi:  
// Buatlah program yang melibatkan:
// - Abstract class Kendaraan
// - Interface Mesin
// - Kelas turunan seperti Mobil, Motor, dll.

// Spesifikasi:
// - Abstract class memiliki method abstrak bergerak().
// - Interface memiliki method hidupkanMesin() dan matikanMesin().
// - Demonstrasikan penggunaan semua konsep dalam main().

// Interface Mesin
// Menyatakan bahwa setiap kendaraan yang memiliki mesin
// harus bisa dihidupkan dan dimatikan.
interface Mesin {
    void hidupkanMesin();
    void matikanMesin();
}

// Abstract Class Kendaraan
// Merupakan abstraksi dari jenis kendaraan apapun.
// Memiliki nama dan method abstrak 'bergerak'.
abstract class Kendaraan {
    String nama;

    // Konstruktor
    public Kendaraan(String nama) {
        this.nama = nama;
    }

    // Method abstrak
    public abstract void bergerak();

    // Method umum untuk menampilkan info kendaraan
    public void info() {
        System.out.println("Nama Kendaraan: " + nama);
    }
}

// Kelas Turunan: Mobil
// Mewarisi dari Kendaraan dan mengimplementasikan interface Mesin.
class Mobil extends Kendaraan implements Mesin {
    public Mobil(String nama) {
        super(nama);
    }

    @Override
    public void hidupkanMesin() {
        System.out.println("Mesin " + nama + " Dihidupkan.");
    }

    @Override
    public void matikanMesin() {
        System.out.println("Mesin " + nama + " Dimatikan.");
    }

    @Override
    public void bergerak() {
        System.out.println(nama + " melaju di jalanan.");
    }
}

// Kelas Turunan: Motor
// Sama seperti Mobil, Motor juga mewarisi Kendaraan dan Mesin.
class Motor extends Kendaraan implements Mesin {
    public Motor(String nama) {
        super(nama);
    }

    @Override
    public void hidupkanMesin() {
        System.out.println("Mesin " + nama + " Dihidupkan.");
    }

    @Override
    public void matikanMesin() {
        System.out.println("Mesin " + nama + " Dimatikan.");
    }

    @Override
    public void bergerak() {
        System.out.println(nama + " melaju di jalanan.");
    }
}

// Kelas Utama: KendaraanApp_Tugas2
// Menunjukkan penggunaan Interface, Abstract Class, dan Polymorphism.
public class KendaraanApp_Tugas2 {
    public static void main(String[] args) {
        // Upcasting ke Kendaraan
        Kendaraan kendaraan1 = new Mobil("Lancia 037");
        Kendaraan kendaraan2 = new Motor("Beat Karbu");

        // Downcasting ke Mesin (untuk akses method interface)
        Mesin mesinMobil = (Mesin) kendaraan1;
        Mesin mesinMotor = (Mesin) kendaraan2;

        // Aksi Mobil
        kendaraan1.info();
        mesinMobil.hidupkanMesin();
        kendaraan1.bergerak();
        mesinMobil.matikanMesin();

        System.out.println("---------------------");

        // Aksi Motor
        kendaraan2.info();
        mesinMotor.hidupkanMesin();
        kendaraan2.bergerak();
        mesinMotor.matikanMesin();
    }
}
