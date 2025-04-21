// Tugas 3: Penggunaan Collection Framework

// Judul: 
// Manajemen Daftar Mahasiswa Menggunakan ArrayList dan HashMap

// Deskripsi:  
// Program ini menyimpan data mahasiswa (nama, NIM, jurusan) menggunakan struktur data
// ArrayList dan HashMap. Program menyediakan menu interaktif untuk:
// 1. Menambah data mahasiswa
// 2. Mencari mahasiswa berdasarkan NIM
// 3. Menampilkan seluruh data mahasiswa

// Import Library

import java.util.ArrayList;   // Untuk menyimpan daftar mahasiswa secara urut
import java.util.HashMap;     // Untuk pencarian cepat berdasarkan NIM
import java.util.Scanner;     // Untuk input dari pengguna

// Class Mahasiswa: merepresentasikan entitas mahasiswa

class Mahasiswa {
    // Properti private agar hanya dapat diakses melalui method (enkapsulasi)
    private String nama;
    private String nim;
    private String jurusan;

    // Constructor: digunakan saat membuat objek Mahasiswa baru
    public Mahasiswa(String nama, String nim, String jurusan) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Getter untuk NIM (digunakan untuk pencarian)
    public String getNim() {
        return nim;
    }

    // Override method toString() untuk menampilkan informasi mahasiswa secara lengkap
    @Override
    public String toString() {
        return "Nama: " + nama + ", NIM: " + nim + ", Jurusan: " + jurusan;
    }
}

// Class MahasiswaApp_Tugas3: program utama

public class MahasiswaApp_Tugas3 {
    public static void main(String[] args) {
        // List untuk menyimpan urutan mahasiswa
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        // Map untuk pencarian mahasiswa berdasarkan NIM
        HashMap<String, Mahasiswa> mapMahasiswa = new HashMap<>();

        // Scanner untuk input dari pengguna
        Scanner input = new Scanner(System.in);

        int pilihan; // Variabel untuk menyimpan pilihan menu
        do {
            // Menampilkan menu utama
            System.out.println("\n=== MENU MANAJEMEN MAHASISWA ===");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Cari Mahasiswa (berdasarkan NIM)");
            System.out.println("3. Tampilkan Semua Data");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = input.nextInt();    // Membaca input menu
            input.nextLine();             // Membersihkan newline dari buffer

            switch (pilihan) {
                case 1:
                    // Menambah data mahasiswa baru
                    System.out.print("Nama: ");
                    String nama = input.nextLine();

                    System.out.print("NIM: ");
                    String nim = input.nextLine();

                    System.out.print("Jurusan: ");
                    String jurusan = input.nextLine();

                    // Membuat objek Mahasiswa
                    Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);

                    // Menambahkan ke ArrayList dan HashMap
                    daftarMahasiswa.add(mhs);
                    mapMahasiswa.put(nim, mhs);

                    System.out.println("Data berhasil ditambahkan.");
                    break;

                case 2:
                    // Mencari mahasiswa berdasarkan NIM
                    System.out.print("Masukkan NIM yang dicari: ");
                    String cariNIM = input.nextLine();

                    // Mengambil data dari HashMap
                    Mahasiswa hasil = mapMahasiswa.get(cariNIM);

                    if (hasil != null) {
                        System.out.println("Ditemukan: " + hasil);
                    } else {
                        System.out.println("Mahasiswa dengan NIM tersebut tidak ditemukan.");
                    }
                    break;

                case 3:
                    // Menampilkan semua data mahasiswa
                    System.out.println("\nDaftar Semua Mahasiswa:");
                    for (Mahasiswa m : daftarMahasiswa) {
                        System.out.println(m);  // Memanggil toString()
                    }
                    break;

                case 0:
                    // Keluar dari program
                    System.out.println("Mampir lagi~.");
                    break;

                default:
                    // Penanganan jika input menu tidak sesuai
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

        } while (pilihan != 0); // Program terus berjalan hingga pengguna memilih 0

        input.close(); // Menutup scanner setelah program selesai
    }
}