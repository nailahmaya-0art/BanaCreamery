package com.mycompany.banacreamery;

public class TransaksiPemesanan {

    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }

    public static void prosesTransaksi(Member member, ProdukEsKrim produk, int porsi) {
        int width = 100;
        System.out.println("\n" + "=".repeat(width));
        System.out.println(centerText("STRUK TRANSAKSI BANACREAMERY", width));
        System.out.println("=".repeat(width));
        System.out.println(" Pembeli (Member) : " + member.getNamaMember() + " [" + member.getIdMember() + "]");
        System.out.println(" Varian Dipilih   : " + produk.getNamaVarian() + " [" + produk.getKodeProduk() + "]");
        
        produk.caraPenyajian();
        
        double total = produk.hitungHargaJual() * porsi;
        System.out.printf(" Jumlah Porsi     : %d porsi%n", porsi);
        System.out.printf(" Total Harga      : Rp%.2f%n", total);
        System.out.println(" Status Transaksi : BERHASIL DICATAT DI SISTEM");
        System.out.println("=".repeat(width));
    }
}