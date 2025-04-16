package lib;

public class TaxData {
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int monthsWorked;
    private int deductible;
    private boolean isMarried;
    private int numberOfChildren;

    // Constructor
    public TaxData(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
        this.monthsWorked = monthsWorked;
        this.deductible = deductible;
        this.isMarried = isMarried;
        this.numberOfChildren = numberOfChildren;
    }

    // Getter (tambahkan kalau perlu setter juga)
    public int getMonthlySalary() { return monthlySalary; }
    public int getOtherMonthlyIncome() { return otherMonthlyIncome; }
    public int getMonthsWorked() { return monthsWorked; }
    public int getDeductible() { return deductible; }
    public boolean isMarried() { return isMarried; }
    public int getNumberOfChildren() { return numberOfChildren; }
}

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(TaxData data) {
		int tax = 0;

		int numberOfMonthWorking = data.getMonthsWorked();
		int monthlySalary = data.getMonthlySalary();
		int otherMonthlyIncome = data.getOtherMonthlyIncome();
		int deductible = data.getDeductible();
		boolean isMarried = data.isMarried();
		int numberOfChildren = data.getNumberOfChildren();

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 months working per year");
		}

		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}

		int nonTaxableIncome = 54000000;
		if (isMarried) {
			nonTaxableIncome += 4500000 + (numberOfChildren * 1500000);
		}

		double taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible - nonTaxableIncome;
		tax = (int) Math.round(0.05 * taxableIncome);

		return Math.max(tax, 0);
	}

		
}

//Test