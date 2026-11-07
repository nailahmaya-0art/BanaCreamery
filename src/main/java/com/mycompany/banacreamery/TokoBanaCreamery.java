package com.mycompany.banacreamery;

import java.util.ArrayList;
import java.util.HashMap;

public class TokoBanaCreamery {
    private String namaToko;
    private DapurProduksi dapurUtama;
    private ArrayList<ProdukEsKrim> inventory;
    private HashMap<String, Member> dataMember;

    public TokoBanaCreamery(String namaToko) {
        this.namaToko = namaToko;
        this.dapurUtama = new DapurProduksi("Dapur Utama Artisan", 500);
        this.inventory = new ArrayList<>();
        this.dataMember = new HashMap<>();
    }

    public void bukaToko() {
        System.out.println("\nMenginisialisasi Sistem " + this.namaToko);
        dapurUtama.statusDapur();
    }

    public void tambahProduk(ProdukEsKrim p) {
        inventory.add(p);
        System.out.println("   [✓] Produk berhasil ditambahkan. (Total Inventory: " + inventory.size() + ")");
    }

    public void lihatSemuaProduk() {
        if (inventory.isEmpty()) {
            System.out.println("   Belum ada produk tersimpan.");
            return;
        }
        cetakHeaderTabel();
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("| %-3d ", (i + 1));
            inventory.get(i).tampilkanInfo();

            inventory.get(i).hitungDiskonKadaluarsa();

            if (inventory.get(i) instanceof DapatDipesan itemBisaDipesan) {
                itemBisaDipesan.prosesPemesananKhusus();
            }

            if (inventory.get(i) instanceof DapatDinilai itemBisaDinilai) {
                itemBisaDinilai.beriRating(5);
            }
            System.out.println("-".repeat(120));
        }
    }

    public void cariProduk(String nama) throws ProdukTidakDitemukanException {
        boolean ditemukan = false;
        System.out.println("\nHasil Pencarian Varian: \"" + nama + "\"");
        for (ProdukEsKrim p : inventory) {
            if (p.getNamaVarian().equalsIgnoreCase(nama) || p.getNamaVarian().toLowerCase().contains(nama.toLowerCase())) {
                p.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new ProdukTidakDitemukanException("   [!] Produk dengan nama varian tersebut tidak ditemukan.");
        }
    }

    public void tambahMember(Member m) {
        dataMember.put(m.getIdMember(), m);
        System.out.println("   [✓] Member " + m.getNamaMember() + " berhasil terdaftar!");
    }

    public void cariMember(String id) {
        if (dataMember.containsKey(id)) {
            System.out.println("   Ditemukan! Nama Member: " + dataMember.get(id).getNamaMember());
        } else {
            System.out.println("   Member dengan ID tersebut tidak terdaftar.");
        }
    }

    public void lihatSemuaMember() {
        System.out.println("Total Member Terdaftar: " + dataMember.size());
        for (Member m : dataMember.values()) {
            System.out.println("- ID: " + m.getIdMember() + " | Nama: " + m.getNamaMember());
        }
    }

    public Member getMember(String id) { return dataMember.get(id); }
    public ProdukEsKrim getProduk(int index) { return inventory.get(index); }
    public int getJumlahProduk() { return inventory.size(); }

    private static void cetakHeaderTabel() {
        System.out.println("=".repeat(120));
        System.out.printf("| %-3s | %-6s | %-18s | %-11s | %-4s | %-14s | %-38s |%n", 
                "No", "Kode", "Nama Varian", "Harga Jual", "Stok", "Kategori", "Spesifikasi Tambahan");
        System.out.println("=".repeat(120));
    }
}