package com.mycompany.banacreamery;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

    public void simpanDataKeFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data_banacreamery.dat"))) {
            out.writeObject(this.inventory);
            out.writeObject(this.dataMember);
            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan (Serialization) ke dalam Harddisk!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        File file = new File("data_banacreamery.dat");
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            this.inventory = (ArrayList<ProdukEsKrim>) in.readObject();
            this.dataMember = (HashMap<String, Member>) in.readObject();
            ProdukEsKrim.totalProdukBerhasilDibuat = this.inventory.size();
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang dari penyimpanan.");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }

    public void bacaLogTransaksi() {
        System.out.println("\nRIWAYAT LOG TRANSAKSI BANACREAMERY");
        File fileLog = new File("log_transaksi.txt");
        if (!fileLog.exists()) {
            System.out.println("   Belum ada log transaksi tersimpan.");
            return;
        }

        try (Scanner reader = new Scanner(fileLog)) {
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("   [!] Gagal membuka file log: " + e.getMessage());
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