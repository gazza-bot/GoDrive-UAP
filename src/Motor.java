public class Motor extends Kendaraan {
    private String jenisTransmisi;
    public Motor(String kode, String nama, double harga, String jenisTransmisi){
        super(kode, nama , harga, true);
        this.jenisTransmisi = jenisTransmisi;
    }

    public void setjenisTransmisi(String jenis){
        this.jenisTransmisi = jenis;
    }

    public String getJenisTransmisi(){
        return this.jenisTransmisi;
    }
    
    @Override
    public void tampilInfo(){
        System.out.printf("[MOBIL] Kode: %s  | Nama: %s \t| Jenis Transmisi : %s | Tarif: Rp%,.0f/hari | Status: %s\n", this.getKodeKendaraan(),this.getNamaKendaraan(),this.jenisTransmisi, this.getHargaSewaPerHari(), this.isTersedia() ? "Tersedia" : "Tidak Tersedia" );

    }

    @Override
    public double hitungBiayaDasar(int lamaSewa){
        if(jenisTransmisi.equals("Matic")){
            double asuransi = 10000 * lamaSewa;
            return (this.getHargaSewaPerHari() * lamaSewa) + asuransi;
        }else {
            return (this.getHargaSewaPerHari() * lamaSewa);
        }
    }
}
