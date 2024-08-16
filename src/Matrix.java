public class Matrix {
    private final double[][] cells;

    public Matrix(double[][] cells) {
        double[][] cel = new double[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[0].length; j++){
                cel[i][j] = cells[i][j];
            }
        }
        this.cells = cel;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++){
                string += String.format("%10.6f", this.getAt(i, j));
            }
            string += "\n";
        }
        return string;

    }

    public double[][] toArray() {
        int linhas = this.getRows();
        int colunas = this.getColumns();
        double[][] array = new double[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                array[i][j] = this.getAt(i, j);
            }
        }
        return array;
    }
    
    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }
    
    public double getAt(int i, int j) {
        if (i >= getRows() || j >= getColumns()) {
            throw new IllegalArgumentException("Indice fora da matriz");
        }
        return cells[i][j];
    }

    public Matrix plus(Matrix m) {
        if (this.getRows() != m.getRows() || this.getColumns() != m.getColumns()) {
            throw new IllegalArgumentException("Matrizes com dimensoes incompativeis");
        }
        int linhas = this.getRows();
        int colunas = this.getColumns();
        double[][] soma = new double[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                soma[i][j] = this.getAt(i, j) + m.getAt(i, j);
            }
        }
        return new Matrix(soma);
    }

    public Matrix minus(Matrix m) {
        if (this.getRows() != m.getRows() || this.getColumns() != m.getColumns()) {
            throw new IllegalArgumentException("Matrizes com dimensoes incompativeis");
        }
        int linhas = this.getRows();
        int colunas = this.getColumns();
        double[][] diferenca = new double[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                diferenca[i][j] = this.getAt(i, j) - m.getAt(i, j);
            }
        }
        return new Matrix(diferenca);
    }

    public Matrix times(double e) {
        int linhas = this.getRows();
        int colunas = this.getColumns();
        double[][] produtoEscalar = new double[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                produtoEscalar[i][j] = this.getAt(i, j) * e;
            }
        }
        return new Matrix(produtoEscalar);
    }

    public Matrix times(Matrix m) {
        if (this.getColumns() != m.getRows()){
            throw new IllegalArgumentException("A multiplicacao nao pode ser feita");
        }
        int linhas1 = this.getRows();
        int colunas1 = this.getColumns(); 
        int colunas2 = m.getColumns();
        double[][] produtoMatriz = new double[linhas1][colunas2];

        for (int i = 0; i < linhas1; i++) {
            for (int j = 0; j < colunas2; j++){
                for (int k = 0; k < colunas1; k++){
                    produtoMatriz[i][j] += this.getAt(i, k) * m.getAt(k, j);
                }
            }
        }
        return new Matrix(produtoMatriz);
    }

    public Matrix getTranspose() {
        int linhas = this.getRows();
        int colunas = this.getColumns();
        double[][] transposta = new double[colunas][linhas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                transposta[j][i] = this.getAt(i, j);
            }
        }
        return new Matrix(transposta);
    }
    
    public boolean isSquare() {
        return (this.getRows() == this.getColumns());
    }
    
    public boolean isSymmetric() {
        int linhas = this.getRows();
        int colunas = this.getColumns();
        Matrix transposta = getTranspose();
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++){
                if (transposta.getAt(i, j) != this.getAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Matrix a = new Matrix(new double[][] {
            {1.0, 2.0, 3.0},
            {3.0, 4.0, 5.0}
          });
          Matrix b = new Matrix(new double[][] {
            {1.0, 2.0},
            {3.0, 4.0},
            {5.0, 6.0}
          });
          Matrix c = a.times(b);
          
          System.out.print(c.toString());
    }
}
