package com.mycompany.banacreamery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BanaCreamery {

    public static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }

    public static void cariProduk(ArrayList<ProdukEsKrim> daftar, String nama) throws ProdukTidakDitemukanException {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Varian: \"" + nama + "\" ---");
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getNamaVarian().equalsIgnoreCase(nama) || 
                daftar.get(i).getNamaVarian().toLowerCase().contains(nama.toLowerCase())) {
                daftar.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new ProdukTidakDitemukanException("   [!] Produk dengan nama varian tersebut tidak ditemukan.");
        }
    }

    public static void cariProduk(ArrayList<ProdukEsKrim> daftar, String kode, boolean isKodeExact) throws ProdukTidakDitemukanException {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Kode: \"" + kode + "\" ---");
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).getKodeProduk().equalsIgnoreCase(kode)) {
                daftar.get(i).tampilkanInfo();
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) {
            throw new ProdukTidakDitemukanException("   [!] Produk dengan Kode [" + kode + "] tidak ditemukan.");
        }
    }

    public static void cariProduk(ArrayList<ProdukEsKrim> daftar, double hargaMaksimal) throws ProdukTidakDitemukanException {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian Produk <= Rp" + String.format("%.2f", hargaMaksimal) + " ---");
        for (int i = 0; i < daftar.size(); i++) {
            if (daftar.get(i).hitungHargaJual() <= hargaMaksimal) {
                daftar.get(i).tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new ProdukTidakDitemukanException("   [!] Tidak ada produk dengan harga di bawah Rp" + hargaMaksimal);
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
        System.out.printf("| %-3s | %-6s | %-18s | %-11s | %-4s | %-14s | %-38s |%n", 
                "No", "Kode", "Nama Varian", "Harga Jual", "Stok", "Kategori", "Spesifikasi Tambahan");
        System.out.println("=".repeat(120));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<ProdukEsKrim> inventory = new ArrayList<>();
        HashMap<String, String> dataMember = new HashMap<>();

        dataMember.put("MEM001", "Budi Santoso");
        dataMember.put("MEM002", "Siti Aminah");

        try {
            inventory.add(new Gelato("GEL01", "Pistachio Crunch", 30000, 15, 4.2, "Milk-Based"));
            inventory.add(new Gelato("GEL02", "Mango Sorbetto", 25000, 20, 0.0, "Water-Based"));
            inventory.add(new EsKrimPop("POP01", "Berry Blast Pop", 15000, 30, "Kayu Pinus", true));
            inventory.add(new EsKrimPop("POP02", "Matcha Dip", 18000, 12, "Bambu Edible", false));
            inventory.add(new Sundae("SUN01", "Choco Lava Delight", 22000, 10, "Oreo & Fudge", 2));
        } catch (Exception e) {
            System.out.println("   [!] Inisialisasi awal gagal: " + e.getMessage());
        }

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
            System.out.println(" 4. Kelola Member Pelanggan");
            System.out.println(" 5. Simulasi Transaksi");
            System.out.println(" 6. Lihat Total Objek Dibuat");
            System.out.println(" 7. Keluar dari Sistem");
            System.out.println("-".repeat(menuWidth));
            System.out.print(" Pilihan Menu (1-7): ");

            try {
                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
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

                            inventory.add(new Gelato(kode, nama, harga, stok, lemak, base));
                            System.out.println("\n   [✓] Gelato baru berhasil ditambahkan!");

                        } else if (tipe == 2) {
                            System.out.print("Masukkan Jenis Stik: ");
                            String stik = scanner.nextLine();
                            System.out.print("Apakah menggunakan Coating Cokelat? (true/false): ");
                            boolean coating = scanner.nextBoolean();
                            scanner.nextLine();

                            inventory.add(new EsKrimPop(kode, nama, harga, stok, stik, coating));
                            System.out.println("\n   [✓] Es Krim Pop baru berhasil ditambahkan!");

                        } else if (tipe == 3) {
                            System.out.print("Masukkan Jenis Topping: ");
                            String topping = scanner.nextLine();
                            System.out.print("Masukkan Jumlah Scoop: ");
                            int scoop = scanner.nextInt();
                            scanner.nextLine();

                            inventory.add(new Sundae(kode, nama, harga, stok, topping, scoop));
                            System.out.println("\n   [✓] Sundae baru berhasil ditambahkan!");

                        } else {
                            System.out.println("   [!] Pilihan jenis produk tidak valid.");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- DAFTAR INVENTORY PRODUK BANACREAMERY ---");
                        if (inventory.isEmpty()) {
                            System.out.println("   Belum ada data produk tersimpan.");
                        } else {
                            cetakHeaderTabel();
                            for (int i = 0; i < inventory.size(); i++) {
                                System.out.printf("| %-3d ", (i + 1));
                                inventory.get(i).tampilkanInfo();

                                inventory.get(i).hitungDiskonKadaluarsa();

                                if (inventory.get(i) instanceof DapatDipesan) {
                                    DapatDipesan itemBisaDipesan = (DapatDipesan) inventory.get(i);
                                    itemBisaDipesan.prosesPemesananKhusus();
                                }

                                if (inventory.get(i) instanceof DapatDinilai) {
                                    DapatDinilai itemBisaDinilai = (DapatDinilai) inventory.get(i);
                                    itemBisaDinilai.beriRating(5);
                                }
                                System.out.println("-".repeat(120));
                            }
                        }
                        break;

                    case 3:
                        if (inventory.isEmpty()) {
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

                        try {
                            if (optCari == 1) {
                                System.out.print("Masukkan Kata Kunci Nama Varian: ");
                                String qNama = scanner.nextLine();
                                cariProduk(inventory, qNama);
                            } else if (optCari == 2) {
                                System.out.print("Masukkan Kode Produk Persis: ");
                                String qKode = scanner.nextLine();
                                cariProduk(inventory, qKode, true);
                            } else if (optCari == 3) {
                                System.out.print("Masukkan Batas Harga Maksimum (Rp): ");
                                double qHarga = scanner.nextDouble();
                                scanner.nextLine();
                                cariProduk(inventory, qHarga);
                            } else {
                                System.out.println("   [!] Pilihan fitur pencarian tidak valid.");
                            }
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        } finally {
                            System.out.println("-> Sesi pencarian selesai dieksekusi.");
                        }
                        break;

                    case 4:
                        System.out.println("\n--- KELOLA MEMBER PELANGGAN (HASHMAP) ---");
                        System.out.println("1. Tambah Member Baru");
                        System.out.println("2. Cari Member via ID/Kode");
                        System.out.println("3. Tampilkan Semua Member");
                        System.out.print("Pilih (1-3): ");
                        int menuMember = scanner.nextInt();
                        scanner.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID/Kode Member (Key): ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap (Value): ");
                            String namaMember = scanner.nextLine();
                            dataMember.put(id, namaMember);
                            System.out.println("   [✓] Member berhasil ditambahkan!");
                        } else if (menuMember == 2) {
                            System.out.print("Masukkan ID/Kode Member yang dicari: ");
                            String searchId = scanner.nextLine();
                            if (dataMember.containsKey(searchId)) {
                                System.out.println("   Ditemukan! Nama Member: " + dataMember.get(searchId));
                            } else {
                                System.out.println("   Member dengan ID tersebut tidak terdaftar.");
                            }
                        } else if (menuMember == 3) {
                            System.out.println("Total Member Terdaftar: " + dataMember.size());
                            for (String keyId : dataMember.keySet()) {
                                System.out.println("- ID: " + keyId + " | Nama: " + dataMember.get(keyId));
                            }
                        }
                        break;

                    case 5:
                        if (inventory.isEmpty()) {
                            System.out.println("   [!] Inventory masih kosong.");
                            break;
                        }

                        System.out.println("\n--- SIMULASI TRANSAKSI PRODUK ---");
                        for (int i = 0; i < inventory.size(); i++) {
                            System.out.printf(" %d. %-20s [%s]%n", (i + 1), inventory.get(i).getNamaVarian(), inventory.get(i).getKodeProduk());
                        }
                        System.out.print("Pilih Nomor Produk (1-" + inventory.size() + "): ");
                        int pilihanProduk = scanner.nextInt();
                        System.out.print("Masukkan Jumlah Porsi: ");
                        int porsi = scanner.nextInt();
                        scanner.nextLine();

                        if (pilihanProduk > 0 && pilihanProduk <= inventory.size()) {
                            simulasiTransaksi(inventory.get(pilihanProduk - 1), porsi);
                        } else {
                            System.out.println("   [!] Nomor produk tidak valid.");
                        }
                        break;

                    case 6:
                        System.out.println("\n--- INFORMASI STATISTIK DIBUAT ---");
                        System.out.println(" Total Produk Terdaftar Saat Ini : " + inventory.size());
                        System.out.println(" Total Objek Berhasil Dibuat     : " + ProdukEsKrim.totalProdukBerhasilDibuat + " instansiasi.");
                        break;

                    case 7:
                        isRunning = false;
                        System.out.println("\n" + "=".repeat(menuWidth));
                        System.out.println(centerText("Terima kasih telah menggunakan sistem toko BanaCreamery!", menuWidth));
                        System.out.println("=".repeat(menuWidth));
                        break;

                    default:
                        System.out.println("\n   [!] Pilihan menu tidak valid, silakan ulangi.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Format input tidak valid! Anda harus memasukkan angka.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n[ERROR SISTEM] Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
    }
}