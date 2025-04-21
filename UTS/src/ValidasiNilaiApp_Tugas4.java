// Tugas 4: Validasi Nilai Menggunakan Custom Exception

// Import Scanner untuk membaca input dari pengguna
import java.util.Scanner;

// Custom Exception: NilaiTidakValidException
// Digunakan untuk melempar kesalahan jika nilai tidak dalam rentang 0-100

class NilaiTidakValidException extends Exception {
    // Konstruktor menerima pesan kesalahan dan meneruskannya ke superclass Exception
    public NilaiTidakValidException(String message) {
        super(message);
    }
}

// Kelas Utama: ValidasiNilaiApp_Tugas4
// Menjalankan program validasi nilai mahasiswa

public class ValidasiNilaiApp_Tugas4 {

    // Method: validasiNilai
    // Memvalidasi apakah nilai berada pada rentang 0-100
    // Jika tidak valid, melemparkan NilaiTidakValidException
    public static void validasiNilai(int nilai) throws NilaiTidakValidException {
        if (nilai < 0 || nilai > 100) {
            throw new NilaiTidakValidException("Nilai harus antara 0 hingga 100!");
        }
    }

    // Method main: titik masuk program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Untuk membaca input dari user
        boolean ulangi = true;                      // Kontrol perulangan jika input tidak valid

        while (ulangi) {
            try {
                // Meminta input dari pengguna
                System.out.print("Masukkan nilai mahasiswa (0 - 100): ");
                int nilai = scanner.nextInt();      // Membaca input sebagai integer

                // Memanggil method validasi
                validasiNilai(nilai);

                // Jika valid, cetak nilai dan keluar dari loop
                System.out.println("Nilai valid: " + nilai);
                ulangi = false;

            } catch (NilaiTidakValidException e) {
                // Tangkap kesalahan jika nilai di luar rentang
                System.out.println(e.getMessage());

            } catch (Exception e) {
                // Tangkap kesalahan jika input bukan angka
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // Bersihkan buffer untuk menghindari loop tak terbatas
            }
        }

        // Menutup scanner setelah selesai
        scanner.close();
    }
}