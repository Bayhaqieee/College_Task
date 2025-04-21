// Kelas Induk
// Tugas 1: Review Konsep Inheritance dan Polymorphism

// Judul:
// Simulasi Pegawai Perusahaan Menggunakan Pewarisan

// Deskripsi: 
// Buat program Java dengan struktur kelas berikut:
// - Pegawai (kelas induk)
// - Manager dan Programmer (kelas turunan)

// Spesifikasi:
// - Setiap kelas memiliki atribut dan method khusus.
// - Gunakan override untuk menghitung gaji masing-masing jenis pegawai.
// - Gunakan upcasting dan polymorphism dalam program utama.

// Kelas Induk: Pegawai
// Kelas ini merupakan superclass dari Manager dan Programmer.
// Memiliki atribut dasar dan method yang akan dioverride di subclass.
class Pegawai {
    String nama;
    String jabatan;

    // Konstruktor
    public Pegawai(String nama, String jabatan) {
        this.nama = nama;
        this.jabatan = jabatan;
    }

    // Method untuk menghitung gaji (di-override oleh subclass)
    public int hitungGaji() {
        return 0;
    }

    // Method untuk menampilkan informasi pegawai
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jabatan: " + jabatan);
        System.out.println("Gaji Total: Rp " + String.format("%,d", hitungGaji()).replace(',', '.'));
        System.out.println("------------------------");
    }
}

// Kelas Turunan: Manager
// Subclass dari Pegawai.
// Menambahkan atribut tunjangan dan override method hitungGaji().
class Manager extends Pegawai {
    int tunjangan;

    // Konstruktor
    public Manager(String nama) {
        super(nama, "Manager");
        this.tunjangan = 5000000;
    }

    // Override method hitungGaji dari kelas induk
    @Override
    public int hitungGaji() {
        return 10000000 + tunjangan;
    }
}

// Kelas Turunan: Programmer
// Subclass dari Pegawai.
// Menambahkan atribut bonus dan override method hitungGaji().
class Programmer extends Pegawai {
    int bonus;

    // Konstruktor
    public Programmer(String nama) {
        super(nama, "Programmer");
        this.bonus = 3000000;
    }

    // Override method hitungGaji dari kelas induk
    @Override
    public int hitungGaji() {
        return 5000000 + bonus;
    }
}

// Kelas Utama: PegawaiApp_Tugas1
// Berisi main method untuk menjalankan program.
// Menunjukkan penggunaan upcasting dan polymorphism.
public class PegawaiApp_Tugas1 {
    public static void main(String[] args) {
        // Upcasting: objek Manager dan Programmer diperlakukan sebagai Pegawai
        Pegawai pegawai1 = new Manager("Yanto");
        Pegawai pegawai2 = new Programmer("Momo");

        // Polymorphism: method tampilkanInfo akan memanggil hitungGaji sesuai objek aslinya
        pegawai1.tampilkanInfo();
        pegawai2.tampilkanInfo();
    }
}