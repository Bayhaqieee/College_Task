// Tugas 5: Menyimpan dan Membaca Data Mahasiswa ke/dari File

import java.io.*;              // Untuk operasi file input/output
import java.util.Scanner;      // Untuk membaca input dari pengguna

// Kelas Utama: FileMahasiswa_Tugas5
// Menyediakan menu untuk menyimpan dan membaca data mahasiswa dari file teks

public class FileMahasiswa_Tugas5 {

    // Nama file konstan tempat data mahasiswa disimpan
    private static final String FILE_NAME = "data_mahasiswa.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner untuk input user
        int pilihan;

        // Menu utama berulang hingga user memilih keluar
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Tulis Data Mahasiswa ke File");
            System.out.println("2. Baca Data Mahasiswa dari File");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = scanner.nextInt();  // Membaca input pilihan
            scanner.nextLine();           // Membersihkan newline di buffer

            // Switch berdasarkan pilihan menu
            switch (pilihan) {
                case 1:
                    tulisDataMahasiswa(scanner); // Menulis data ke file
                    break;
                case 2:
                    bacaDataMahasiswa();         // Membaca data dari file
                    break;
                case 0:
                    System.out.println("Terima kasih!"); // Keluar program
                    break;
                default:
                    System.out.println("Pilihan tidak valid."); // Validasi input
            }
        } while (pilihan != 0);

        scanner.close(); // Tutup scanner setelah program selesai
    }

    // Method: tulisDataMahasiswa
    // Tujuan: Meminta input user lalu menyimpan data ke file teks
    public static void tulisDataMahasiswa(Scanner scanner) {
        // Try-with-resources untuk otomatis menutup BufferedWriter
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Input data mahasiswa dari user
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan NIM: ");
            String nim = scanner.nextLine();

            System.out.print("Masukkan Jurusan: ");
            String jurusan = scanner.nextLine();

            // Tulis data ke file dengan format sederhana
            writer.write("Nama: " + nama);
            writer.newLine();

            writer.write("NIM: " + nim);
            writer.newLine();

            writer.write("Jurusan: " + jurusan);
            writer.newLine();

            writer.write("----------------------"); // Pembatas antar data
            writer.newLine();

            System.out.println("Data berhasil ditulis ke file.");

        } catch (IOException e) {
            System.out.println("Gagal menulis ke file: " + e.getMessage());
        }
    }

    // Method: bacaDataMahasiswa
    // Tujuan: Menampilkan isi file teks yang berisi data mahasiswa
    public static void bacaDataMahasiswa() {
        // Try-with-resources untuk otomatis menutup BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n===== DATA MAHASISWA =====");

            // Baca file baris per baris
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan."); // Jika file belum dibuat
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
    }
}