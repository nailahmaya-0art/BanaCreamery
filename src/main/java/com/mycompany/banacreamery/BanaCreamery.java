package com.mycompany.banacreamery;

import java.util.Scanner;

public class BanaCreamery {

    public static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }

    public static void cariProduk(ProdukEsKrim[] daftar, int jumlah, String nama) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Varian: \"" + nama + "\" ---");
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getNamaVarian().equalsIgnoreCase(nama) || 
                daftar[i].getNamaVarian().toLowerCase().contains(nama.toLowerCase())) {
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("   [!] Produk dengan nama varian tersebut tidak ditemukan.");
        }
    }

    public static void cariProduk(ProdukEsKrim[] daftar, int jumlah, String kode, boolean isKodeExact) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Kode: \"" + kode + "\" ---");
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getKodeProduk().equalsIgnoreCase(kode)) {
                daftar[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("   [!] Produk dengan Kode [" + kode + "] tidak ditemukan.");
        }
    }

    public static void cariProduk(ProdukEsKrim[] daftar, int jumlah, double hargaMaksimal) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Produk <= Rp" + String.format("%.2f", hargaMaksimal) + " ---");
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].hitungHargaJual() <= hargaMaksimal) {
                daftar[i].tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("   [!] Tidak ada produk dengan harga di bawah Rp" + hargaMaksimal);
        }
    }

    public static void simulasiTransaksi(ProdukEsKrim item, int porsi) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println(centerText("SIMULASI TRANSAKSI BANA CREAMERY", 100));
        System.out.println("=".repeat(100));
        System.out.println(" Varian Dipilih : " + item.getNamaVarian() + " [" + item.getKodeProduk() + "]");
        
        item.caraPenyajian(); 
        
        double total = item.hitungHargaJual() * porsi;
        System.out.printf(" Jumlah Porsi   : %d porsi%n", porsi);
        System.out.printf(" Total Harga    : Rp%.2f%n", total);
        System.out.println("=".repeat(100));
    }
    
    private static void cetakHeaderTabel() {
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-7s | %-20s | %-13s | %-4s | %-15s | %-25s |%n", 
                "No", "Kode", "Nama Varian", "Harga Jual", "Stok", "Kategori", "Spesifikasi Tambahan");
        System.out.println("=".repeat(120));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdukEsKrim[] inventory = new ProdukEsKrim[20];
        int jumlahProduk = 0;

        inventory[jumlahProduk++] = new Gelato("GEL01", "Pistachio Crunch", 30000, 15, 4.2, "Milk-Based");
        inventory[jumlahProduk++] = new Gelato("GEL02", "Mango Sorbetto", 25000, 20, 0.0, "Water-Based");
        inventory[jumlahProduk++] = new EsKrimPop("POP01", "Berry Blast Pop", 15000, 30, "Kayu Pinus", true);
        inventory[jumlahProduk++] = new EsKrimPop("POP02", "Matcha Dip", 110000, 12, "Bambu Edible", false);
        inventory[jumlahProduk++] = new Sundae("SUN01", "Choco Lava Delight", 22000, 10, "Oreo & Fudge", 2);
        
        boolean isRunning = true;
        int menuWidth = 100;

        while (isRunning) {
            System.out.println("\n" + "=".repeat(menuWidth));
            System.out.println(centerText("SISTEM MANAJEMEN GELATO & ES KRIM ARTISAN", menuWidth));
            System.out.println(centerText("BANACREAMERY", menuWidth));
            System.out.println("=".repeat(menuWidth));
            System.out.println(" 1. Tambah Data Produk Baru");
            System.out.println(" 2. Tampilkan Seluruh Produk");
            System.out.println(" 3. Cari Produk");
            System.out.println(" 4. Simulasi Transaksi (Polymorphism)");
            System.out.println(" 5. Lihat Total Objek Dibuat");
            System.out.println(" 6. Keluar dari Sistem");
            System.out.println("-".repeat(menuWidth));
            System.out.print(" Pilihan Menu (1-6): ");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    if (jumlahProduk >= inventory.length) {
                        System.out.println("\n   [!] Kapasitas penyimpanan inventory penuh!");
                        break;
                    }

                    System.out.println("\n--- TAMBAH PRODUK BARU BANACREAMERY ---");
                    System.out.println("Pilih Jenis Produk:");
                    System.out.println("1. Gelato (Artisan)");
                    System.out.println("2. Es Krim Pop (Popsicle)");
                    System.out.println("3. Sundae (Deluxe)");
                    System.out.print("Pilihan Subclass (1-3): ");
                    int tipe = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Masukkan Kode Produk: ");
                    String kode = scanner.nextLine();
                    System.out.print("Masukkan Nama Varian: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Harga Dasar (Rp): ");
                    double harga = scanner.nextDouble();
                    System.out.print("Masukkan Jumlah Stok: ");
                    int stok = scanner.nextInt();
                    scanner.nextLine();

                    if (tipe == 1) {
                        System.out.print("Masukkan Persentase Lemak Susu (%): ");
                        double lemak = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Masukkan Base Rasa: ");
                        String base = scanner.nextLine();

                        inventory[jumlahProduk++] = new Gelato(kode, nama, harga, stok, lemak, base);
                        System.out.println("\n   [✓] Gelato baru berhasil ditambahkan!");

                    } else if (tipe == 2) {
                        System.out.print("Masukkan Jenis Stik: ");
                        String stik = scanner.nextLine();
                        System.out.print("Apakah menggunakan Coating Cokelat? (true/false): ");
                        boolean coating = scanner.nextBoolean();
                        scanner.nextLine();

                        inventory[jumlahProduk++] = new EsKrimPop(kode, nama, harga, stok, stik, coating);
                        System.out.println("\n   [✓] Es Krim Pop baru berhasil ditambahkan!");

                    } else if (tipe == 3) {
                        System.out.print("Masukkan Jenis Topping: ");
                        String topping = scanner.nextLine();
                        System.out.print("Masukkan Jumlah Scoop: ");
                        int scoop = scanner.nextInt();
                        scanner.nextLine();

                        inventory[jumlahProduk++] = new Sundae(kode, nama, harga, stok, topping, scoop);
                        System.out.println("\n   [✓] Sundae baru berhasil ditambahkan!");

                    } else {
                        System.out.println("   [!] Pilihan jenis produk tidak valid.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- DAFTAR INVENTORY PRODUK BANACREAMERY ---");
                    if (jumlahProduk == 0) {
                        System.out.println("   Belum ada data produk tersimpan.");
                    } else {
                        cetakHeaderTabel();
                        for (int i = 0; i < jumlahProduk; i++) {
                            System.out.printf("| %-3d ", (i + 1));
                            inventory[i].tampilkanInfo();
                        }
                        System.out.println("=".repeat(120));
                    }
                    break;

                case 3:
                    if (jumlahProduk == 0) {
                        System.out.println("   [!] Inventory masih kosong.");
                        break;
                    }

                    System.out.println("\n--- FITUR PENCARIAN PRODUK ---");
                    System.out.println("1. Cari Berdasarkan Nama Varian");
                    System.out.println("2. Cari Berdasarkan Kode Produk");
                    System.out.println("3. Cari Berdasarkan Harga Maksimal");
                    System.out.print("Pilihan Metode Cari (1-3): ");
                    int optCari = scanner.nextInt();
                    scanner.nextLine();

                    if (optCari == 1) {
                        System.out.print("Masukkan Kata Kunci Nama Varian: ");
                        String qNama = scanner.nextLine();
                        cariProduk(inventory, jumlahProduk, qNama);
                    } else if (optCari == 2) {
                        System.out.print("Masukkan Kode Produk Persis: ");
                        String qKode = scanner.nextLine();
                        cariProduk(inventory, jumlahProduk, qKode, true);
                    } else if (optCari == 3) {
                        System.out.print("Masukkan Batas Harga Maksimum (Rp): ");
                        double qHarga = scanner.nextDouble();
                        scanner.nextLine();
                        cariProduk(inventory, jumlahProduk, qHarga);
                    } else {
                        System.out.println("   [!] Pilihan fitur pencarian tidak valid.");
                    }
                    break;

                case 4:
                    if (jumlahProduk == 0) {
                        System.out.println("   [!] Inventory masih kosong.");
                        break;
                    }

                    System.out.println("\n--- SIMULASI TRANSAKSI PRODUK ---");
                    for (int i = 0; i < jumlahProduk; i++) {
                        System.out.printf(" %d. %-20s [%s]%n", (i + 1), inventory[i].getNamaVarian(), inventory[i].getKodeProduk());
                    }
                    System.out.print("Pilih Nomor Produk (1-" + jumlahProduk + "): ");
                    int pilihanProduk = scanner.nextInt();
                    System.out.print("Masukkan Jumlah Porsi: ");
                    int porsi = scanner.nextInt();
                    scanner.nextLine();

                    if (pilihanProduk > 0 && pilihanProduk <= jumlahProduk) {
                        simulasiTransaksi(inventory[pilihanProduk - 1], porsi);
                    } else {
                        System.out.println("   [!] Nomor produk tidak valid.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- INFORMASI STATISTIK DIBUAT ---");
                    System.out.println(" Total Produk Terdaftar Saat Ini : " + jumlahProduk);
                    System.out.println(" Total Objek Berhasil Dibuat     : " + ProdukEsKrim.totalProdukBerhasilDibuat + " instansiasi.");
                    break;

                case 6:
                    isRunning = false;
                    System.out.println("\n" + "=".repeat(menuWidth));
                    System.out.println(centerText("Terima kasih telah menggunakan sistem toko BanaCreamery!", menuWidth));
                    System.out.println("=".repeat(menuWidth));
                    break;

                default:
                    System.out.println("\n   [!] Pilihan menu tidak valid, silakan ulangi.");
                    break;
            }
        }
        scanner.close();
    }
}