package com.mycompany.banacreamery;

public class DapurProduksi {
    private String namaDapur;
    private int kapasitasFreezer;

    public DapurProduksi(String namaDapur, int kapasitasFreezer) {
        this.namaDapur = namaDapur;
        this.kapasitasFreezer = kapasitasFreezer;
    }

    public void statusDapur() {
        System.out.println("-> [" + namaDapur + "] Siap Beroperasi. Kapasitas Freezer: " + kapasitasFreezer + " Liter.");
    }
}