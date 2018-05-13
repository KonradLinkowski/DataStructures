package algoritms;

public class Matrix {
	private int rows;
	private int columns;
	private Number[][] values;
	
	public Matrix(int rows, int columns) {
		this.columns = columns;
		this.rows = rows;
		values = new Number[this.rows][this.columns];
		for (int i = 0; i < columns * rows; i++) {
			set(i / columns, i % columns, new Number(0));
		}
	}
	
	public Matrix(Matrix matrix) {
		this.columns = matrix.columns;
		this.rows = matrix.rows;
		this.values = new Number[this.rows][this.columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				set(i, j, matrix.get(i, j).clone());
			}
		}
	}
	
	public Matrix(int rows, int columns, Number ...values) {
		if (columns * rows != values.length) {
			throw new IllegalArgumentException("Lenght of values have to be equal to columns * rows");
		}
		this.columns = columns;
		this.rows = rows;
		this.values = new Number[this.rows][this.columns];
		for (int i = 0; i < values.length; i++) {
			set(i / columns, i % columns, values[i]);
		}
	}
	
	public Matrix(int rows, int columns, String ...values) {
		if (columns * rows != values.length) {
			throw new IllegalArgumentException("Length of values have to be equal to columns * rows");
		}
		this.columns = columns;
		this.rows = rows;
		this.values = new Number[this.rows][this.columns];
		for (int i = 0; i < values.length; i++) {
			set(i / columns, i % columns, new Number(values[i]));
		}
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		if (a.columns != b.rows) {
			throw new IllegalArgumentException("Columns of matrix a must by equal to rows of matrix b.");
		}
		Matrix result = new Matrix(a.rows, b.columns);
		Number temp;
		int depth = a.columns;
		for (int i = 0; i < result.rows; i++) {
			for (int j = 0; j < result.columns; j++) {
				temp = new Number(0);
				for (int k = 0; k < depth; k++) {
					temp = Number.sum(temp, Number.multiply(a.values[i][k], b.values[k][j])); 
				}
				result.values[i][j] = temp;
			}
		}
		return result;
	}
	
	public Matrix transpose() {
		Matrix result = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				result.set(j, i, this.get(i, j));
			}
		}
		return result;
	}
	
	public Number get(int row, int column) {
		return values[row][column];
	}
	
	public Matrix set(int row, int column, Number value) {
		values[row][column] = value;
		return this;
	}
	
	public int getRows() {
		return rows;
	}
	
	public Matrix setRows(int rows) {
		this.rows = rows;
		return this;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Matrix setColumns(int columns) {
		this.columns = columns;
		return this;
	}
}
