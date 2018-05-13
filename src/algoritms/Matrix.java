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
	
	public Matrix(int rows, int columns, long ...values) {
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
	
	public Matrix multiply(Matrix mat) {
		if (this.columns != mat.rows) {
			throw new IllegalArgumentException(
					"Columns of matrix have to be equal to rows of the given matrix.");
		}
		Matrix result = new Matrix(this.rows, mat.columns);
		Number temp;
		int depth = this.columns;
		for (int i = 0; i < result.rows; i++) {
			for (int j = 0; j < result.columns; j++) {
				temp = new Number(0);
				for (int k = 0; k < depth; k++) {
					temp = temp.add(this.get(i, k).multiply(mat.get(k, j)));
				}
				result.values[i][j] = temp;
			}
		}
		return result;
	}
	
	public Matrix multiply(Number num) {
		Matrix result = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				result.set(i, j, this.get(i, j).multiply(num));
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
	
	public Matrix add(Matrix mat) {
		if (this.columns != mat.columns && this.rows != mat.rows) {
			throw new IllegalArgumentException("Matrices size have to be equal..");
		}
		Matrix result = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				result.set(i, j, this.get(i, j).add(mat.get(i, j)));
			}
		}
		return result;
	}
	
	public Matrix subtract(Matrix mat) {
		if (this.columns != mat.columns && this.rows != mat.rows) {
			throw new IllegalArgumentException("Matrices size have to be equal..");
		}
		Matrix result = new Matrix(this.rows, this.columns);
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				result.set(i, j, this.get(i, j).subtract(mat.get(i, j)));
			}
		}
		return result;
	}
	
	public Number determinant() {
		if (this.rows != this.columns) {
			throw new IllegalArgumentException("Number of rows and columns have to be equal.");
		}
//		System.out.println(this.rows +  " " + this.columns);
		if (this.rows == 1) {
			return this.get(0, 0);
		}
		Number det = new Number(0);
		for (int i = 0; i < this.rows; i++) {
			det = det.add(
				get(i, 0).multiply(
					subMatrix(i, 0).determinant().multiply(i % 2 == 0 ? 1: -1)
				)
			);
		}
		return det;
	}
	
	public Matrix subMatrix(int row, int column) {
		if (row >= this.rows || column >= this.columns) {
			throw new IllegalArgumentException("Too big arguments.");
		}
		if (rows == 1 || columns == 1) {
			throw new IllegalArgumentException("Too small matrix.");
		}
		Matrix mat = new Matrix(this.rows - 1, this.columns - 1);
		for (int i = 0, mati = 0; i < this.rows; i++) {
			if (i == row) continue;
			for (int j = 0, matj = 0; j < this.columns; j++) {
				if (j == column) continue;
				mat.set(mati, matj, this.get(i, j));
				matj++;
			}
			mati++;
		}
		return mat;
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
