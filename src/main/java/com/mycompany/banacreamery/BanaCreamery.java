package com.mycompany.banacreamery;

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        TokoBanaCreamery tokoUtama = new TokoBanaCreamery("BanaCreamery");
        tokoUtama.bukaToko();

        tokoUtama.tambahMember(new Member("MEM001", "Budi Santoso"));
        tokoUtama.tambahMember(new Member("MEM002", "Siti Aminah"));

        try {
            tokoUtama.tambahProduk(new Gelato("GEL01", "Pistachio Crunch", 30000, 15, 4.2, "Milk-Based"));
            tokoUtama.tambahProduk(new Gelato("GEL02", "Mango Sorbetto", 25000, 20, 0.0, "Water-Based"));
            tokoUtama.tambahProduk(new EsKrimPop("POP01", "Berry Blast Pop", 15000, 30, "Kayu Pinus", true));
            tokoUtama.tambahProduk(new EsKrimPop("POP02", "Matcha Dip", 18000, 12, "Bambu Edible", false));
            tokoUtama.tambahProduk(new Sundae("SUN01", "Choco Lava Delight", 22000, 10, "Oreo & Fudge", 2));
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
            System.out.println(" 5. Transaksi Pemesanan Member");
            System.out.println(" 6. Lihat Total Objek Dibuat");
            System.out.println(" 7. Keluar dari Sistem");
            System.out.println("-".repeat(menuWidth));
            System.out.print(" Pilihan Menu (1-7): ");

            try {
                int menu = scanner.nextInt();
                scanner.nextLine();

                switch (menu) {
                    case 1:
                        System.out.println("\nTAMBAH PRODUK BARU BANACREAMERY");
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
                            tokoUtama.tambahProduk(new Gelato(kode, nama, harga, stok, lemak, base));

                        } else if (tipe == 2) {
                            System.out.print("Masukkan Jenis Stik: ");
                            String stik = scanner.nextLine();
                            System.out.print("Apakah menggunakan Coating Cokelat? (true/false): ");
                            boolean coating = scanner.nextBoolean();
                            scanner.nextLine();
                            tokoUtama.tambahProduk(new EsKrimPop(kode, nama, harga, stok, stik, coating));

                        } else if (tipe == 3) {
                            System.out.print("Masukkan Jenis Topping: ");
                            String topping = scanner.nextLine();
                            System.out.print("Masukkan Jumlah Scoop: ");
                            int scoop = scanner.nextInt();
                            scanner.nextLine();
                            tokoUtama.tambahProduk(new Sundae(kode, nama, harga, stok, topping, scoop));

                        } else {
                            System.out.println("   [!] Pilihan jenis produk tidak valid.");
                        }
                        break;

                    case 2:
                        System.out.println("\nDAFTAR INVENTORY PRODUK BANACREAMERY");
                        tokoUtama.lihatSemuaProduk();
                        break;

                    case 3:
                        System.out.println("\nFITUR PENCARIAN PRODUK");
                        System.out.print("Masukkan Kata Kunci Nama Varian: ");
                        String qNama = scanner.nextLine();
                        try {
                            tokoUtama.cariProduk(qNama);
                        } catch (ProdukTidakDitemukanException e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\nKELOLA MEMBER PELANGGAN");
                        System.out.println("1. Tambah Member Baru");
                        System.out.println("2. Cari Member via ID");
                        System.out.println("3. Lihat Semua Member");
                        System.out.print("Pilih (1-3): ");
                        int menuMember = scanner.nextInt();
                        scanner.nextLine();

                        if (menuMember == 1) {
                            System.out.print("Masukkan ID Member: ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap: ");
                            String namaMember = scanner.nextLine();
                            tokoUtama.tambahMember(new Member(id, namaMember));
                        } else if (menuMember == 2) {
                            System.out.print("ID Member yang dicari: ");
                            String searchId = scanner.nextLine();
                            tokoUtama.cariMember(searchId);
                        } else if (menuMember == 3) {
                            tokoUtama.lihatSemuaMember();
                        }
                        break;

                    case 5:
                        System.out.println("\nTRANSAKSI PEMESANAN");
                        System.out.print("Masukkan ID Member Peminjam/Pembeli: ");
                        String idMember = scanner.nextLine();
                        
                        Member pemesan = tokoUtama.getMember(idMember);
                        if (pemesan == null) {
                            System.out.println("   [!] Gagal: Member dengan ID tersebut belum terdaftar!");
                        } else if (tokoUtama.getJumlahProduk() == 0) {
                            System.out.println("   [!] Gagal: Belum ada produk yang tersedia!");
                        } else {
                            tokoUtama.lihatSemuaProduk();
                            System.out.print("Pilih Nomor Produk (1-" + tokoUtama.getJumlahProduk() + "): ");
                            int idxProduk = scanner.nextInt() - 1;
                            System.out.print("Masukkan Jumlah Porsi: ");
                            int porsi = scanner.nextInt();
                            scanner.nextLine();

                            if (idxProduk >= 0 && idxProduk < tokoUtama.getJumlahProduk()) {
                                ProdukEsKrim itemDipilih = tokoUtama.getProduk(idxProduk);
                                TransaksiPemesanan.prosesTransaksi(pemesan, itemDipilih, porsi);
                            } else {
                                System.out.println("   [!] Nomor produk tidak valid.");
                            }
                        }
                        break;

                    case 6:
                        System.out.println("\nINFORMASI STATISTIK DIBUAT");
                        System.out.println(" Total Produk Terdaftar Saat Ini : " + tokoUtama.getJumlahProduk());
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