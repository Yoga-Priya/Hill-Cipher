import java.util.Scanner;
public class HillCipher {
	static int mod;
	static int temp;
	static int quotient;
		public static void main(String[] args) {			
			Scanner k = new Scanner(System.in);
			System.out.println("Enter Your Choice 1.Plain text to cipher text || 2.cipher text to plain text || 3.Encryption key matrix || 4.Decryption key matrix");
	int d = 0;
	int c = k.nextInt();
	switch(c)
	        {
	case 1:
	            {
		System.out.println("Enter The key matrix order:");
		int key_order=k.nextInt();
		int[][] km=new int[key_order][key_order];
		for(int i=0;i<key_order;i++){
			for(int j=0;j<key_order;j++){
			System.out.println("Enter The value for key matrix k["+i+"]["+j+"]");
			int x=k.nextInt();
			km[i][j]=x;
}
		        }
	System.out.println("Enter The Plain text:");
	        String a = k.next();
	int[][] pt;
	int y=0;
	if(a.length()%key_order==0){
		y=a.length()/key_order;
		}
	else
		y=(a.length()/key_order)+1;
	System.out.println("y vlaue "+y);
	pt=new int[key_order][y];
	int x=0;
	int n=0;
	int m=0;
		for(int j=0;j<y;j++){
			for(int i=0;i<key_order;i++){
 m=122;
				if(x<a.length()){
					m=a.charAt(x);
				 }
				else{
				m=122;	
				 }
			if(m>96&&m<123){
				n=m-97;
				}
if(m>64&&m<91){			
		n=m-65;
		}
System.out.println("m "+m+" n "+n);
		pt[i][j]=n;
		x++;
		}
	        }
	int b=26;
	try
	        {
		String ciphertext="";
		int ct[][];
		int z;
		int sum=0;
		ct=new int[key_order][y];
			char decrpt = 0;
			for ( c = 0 ; c <key_order ; c++ )
	                {
	for ( d = 0 ; d < y ; d++ )
	                   {   
	for ( z = 0 ; z <key_order ; z++ )
	                      {
	sum = sum + km[c][z]*pt[z][d];
	System.out.println("sum "+c+" "+d+" "+km[c][z]+" "+pt[z][d]+km[c][z]*pt[z][d]);
	                      }
	
	ct[c][d] = sum;
	sum = 0;
	                   }
	                }
	System.out.println("Product of entered matrices:-");
	for ( d = 0 ; d < y ; d++ ){
		for ( c = 0 ; c <key_order ; c++ )
		                {
	System.out.print(ct[c][d]+"\t");
	int mod=mod(ct[c][d],b);
	if(x<a.length()){
				m=a.charAt(x);}
					else
					m=122;	
				if(m>96&&m<123){
					decrpt=(char)(mod+97);
					ciphertext+=decrpt;
				}
				if(m>64&&m<91){
					decrpt=(char)(mod+65);
					ciphertext+=decrpt;
				}
	System.out.print("\n");
	                }
		}
	
		System.out.println("cipher text is "+ciphertext);
	}catch(ArithmeticException e)
	        {
	System.out.println(e);	
	        }
	break;
	        }	
	case 2:
	            {
		System.out.println("Enter The key matrix order:");
		int key_order=k.nextInt();
		int[][] km=new int[key_order][key_order];
		for(int i=0;i<key_order;i++){
			for(int j=0;j<key_order;j++){
			System.out.println("Enter The value for key matrix k["+i+"]["+j+"]");
			int x=k.nextInt();
			km[i][j]=x;

			}
		        }
	System.out.println("Enter The cipher text:");
	        String a = k.next();
	int b=26;
	try
	        {
		Matrix key=new Matrix(km);
		System.out.println("det value is "+Matrix.determinant(key));
		int vals[]=gcd(mod(Matrix.determinant(key),b),b);
		int det_inv=mod(mod(vals[1],b),b);
		System.out.println("inverse is "+det_inv);
		       Matrix cof=new Matrix(key_order, key_order);
		cof=Matrix.cofactor(key);
		int[][] adj=new int[key_order][key_order]; 
			for(int j=0;j<key_order;j++){
				for(int i=0;i<key_order;i++){
				System.out.print("original "+cof.getValueAt(i, j)+" transpose "+mod(cof.getValueAt(i, j),b)+"\t");
				adj[j][i]=mod(cof.getValueAt(i, j),b);
			   }
				System.out.println("");
		       }
			int[][] inv=new int[key_order][key_order];
				for(int i=0;i<key_order;i++){
				for(int j=0;j<key_order;j++){
					inv[i][j]=mod(adj[i][j]*det_inv,b);
					System.out.print(""+inv[i][j]+"\t");
				   }
				System.out.println();
				   }
			
				int[][] ct;
			int y=0;
			if(a.length()%key_order==0){
				y=a.length()/key_order;
				}
			else
				y=(a.length()/key_order)+1;
			System.out.println("y vlaue "+y);
			ct=new int[key_order][y];
			int x=0;
			int n=0;
			int m=0;
				for(int j=0;j<y;j++){
					for(int i=0;i<key_order;i++){
						 m=122;
						if(x<a.length()){
							m=a.charAt(x);
								 }
							else
							m=122;	
					if(m>96&&m<123){
						n=m-97;
					}
					if(m>64&&m<91){
n=m-65;
					}
		System.out.println("m "+m+" n "+n);
				ct[i][j]=n;
				x++;			
}
			        }
				String plaintext="";
				int pt[][];
				int z;
				int sum=0;			
	pt=new int[key_order][y];
				char decrpt = 0;
				for ( c = 0 ; c <key_order ; c++ )
			                {
			for ( d = 0 ; d < y ; d++ )
			                   {   
			for ( z = 0 ; z <key_order ; z++ )
			                      {
			sum = sum + inv[c][z]*ct[z][d];
			                      }
			
			pt[c][d] = sum;
			sum = 0;
			                   }
			                }
			System.out.println("Product of entered matrices:-");
			        x=0;	
for ( d = 0 ; d < y ; d++ ){
				for ( c = 0 ; c <key_order ; c++ )
				                {
			System.out.print(pt[c][d]+"\t");
			int mod=mod(pt[c][d],b);
			if(x<a.length()){
						m=a.charAt(x);}
							else{
							m=122;	
							 }

						if(m>96&&m<123){
							decrpt=(char)(mod+97);
							plaintext+=decrpt;
						}
						if(m>64&&m<91){
							decrpt=(char)(mod+65);
							plaintext+=decrpt;
						}
			System.out.print("\n");
			x++;
			                }
				}
				System.out.println("Plain text is "+plaintext);
		
	        }
	catch(ArithmeticException e)
	        {
	System.out.println(e);
	
	        }
	break;
	        }
	case 3:
	            {
		int[][] ct_array;
		int[][] pt_array;
int b=26;
		int y=0;
System.out.println("Enter The plain text:");
String pt = k.next();
		System.out.println("Enter The cipher text:");
		String ct = k.next();
		System.out.println("Enter The key order:");
		int key_order = k.nextInt();
		int[][] pm=new int[key_order][key_order];
		int[][] cm=new int[key_order][key_order];
if(pt.length()%key_order==0){
					y=pt.length()/key_order;
					}
			else
				y=(pt.length()/key_order)+1;
		System.out.println("y vlaue "+y);
		pt_array=new int[key_order][y];
		ct_array=new int[key_order][y];
		int x=0;
		int n=0;
			int m=0;
			for(int j=0;j<y;j++){
					for(int i=0;i<key_order;i++){
						 m=122;
						if(x<pt.length()){
						m=pt.charAt(x);						
							 }
							else
							m=122;  				
					if(m>96&&m<123){						
						n=m-97;
					}
					if(m>64&&m<91){
						n=m-65;
					}
				System.out.println("m "+m+" n "+n);
				pt_array[i][j]=n;
				x++;

				}
			        }
				 x=0;
				 n=0;
				 m=0;
				for(int j=0;j<y;j++){
				for(int i=0;i<key_order;i++){
							 m=122;
							if(x<pt.length()){
							m=ct.charAt(x);
							
								 }
								else
								m=122;
if(m>96&&m<123){
							n=m-97;
							}
					if(m>64&&m<91){
							n=m-65;
							}
				System.out.println("m "+m+" n "+n);
					ct_array[i][j]=n;
					x++;
					}
				        }
					for(int i=0;i<key_order;i++){
						for(int j=0;j<key_order;j++){
							pm[i][j]=pt_array[i][j];
							cm[i][j]=ct_array[i][j];

						}
					}
					
					try
		{
		  Matrix key=new Matrix(pm);
		System.out.println("det value is "+Matrix.determinant(key));
		int vals[]=gcd(mod(Matrix.determinant(key),b),b);
		int det_inv=mod(mod(vals[1],b),b);
		System.out.println("inverse is "+det_inv);
		Matrix cof=new Matrix(key_order, key_order);
		cof=Matrix.cofactor(key);
		int[][] adj=new int[key_order][key_order]; 
		for(int j=0;j<key_order;j++){
			for(int i=0;i<key_order;i++){
				System.out.print("original "+cof.getValueAt(i, j)+" transpose "+mod(cof.getValueAt(i, j),b)+"\t");
				adj[j][i]=mod(cof.getValueAt(i, j),b);
						   }
				System.out.println("");
						       }
		int[][] inv=new int[key_order][key_order];
		for(int i=0;i<key_order;i++){
				for(int j=0;j<key_order;j++){
						inv[i][j]=mod(adj[i][j]*det_inv,b);
						System.out.print(""+inv[i][j]+"\t");
								   }
			System.out.println();
					   }
				int pd[][];
				pd=new int[key_order][key_order];
				int kd[][];
				kd=new int[key_order][key_order];
				int z;
				int sum=0;
				char decrpt = 0;
				for ( d = 0 ; d <key_order ; d++ ){
					for ( c = 0 ; c <key_order ; c++ )
							{
		System.out.print("i: "+d+"j: "+c+" inv p: "+inv[d][c]+" c: "+cm[d][c]);
							                }
							}
						for ( c = 0 ; c <key_order ; c++ )
							{
							for ( d = 0 ; d <key_order ; d++ )
							            {   
							for ( int k1 = 0 ; k1 <key_order ; k1++ )
							               {
							sum = sum + cm[c][k1]*inv[k1][d];
							               }
							
							pd[c][d] = sum;
							sum = 0;
							            }
							         }
		System.out.println();
		System.out.println("Encryption key matrix:-");
		for ( d = 0 ; d <key_order ; d++ ){
			for ( c = 0 ; c <key_order ; c++ )
					{
					int mod=mod(pd[d][c],b);
					kd[d][c]=mod;
System.out.print(kd[d][c]+"\t");
					  }
System.out.println();
					 }
						
					        }
					catch(ArithmeticException e)
					        {
					System.out.println(e);
					
					        }
					
		break;
		
	            }
	case 4:
	            {

		int[][] ct_array;
		int[][] pt_array;
int b=26;
				int y=0;
				System.out.println("Enter The plain text:");
			        String ct = k.next();
			System.out.println("Enter The cipher text:");
			        String pt = k.next();
				System.out.println("Enter The key order:");
			int key_order = k.nextInt();
			int[][] pm=new int[key_order][key_order];
			int[][] cm=new int[key_order][key_order];

				if(pt.length()%key_order==0){
					y=pt.length()/key_order;
					}
				else
					y=(pt.length()/key_order)+1;
				System.out.println("y vlaue "+y);
		
		pt_array=new int[key_order][y];
		ct_array=new int[key_order][y];
			int x=0;
			int n=0;
			int m=0;
				for(int j=0;j<y;j++){
					for(int i=0;i<key_order;i++){
						 m=122;
						if(x<pt.length()){
						m=pt.charAt(x);
						
							 }
							else
							m=122;	
							
					
					if(m>96&&m<123){
						
						n=m-97;
					}
		if(m>64&&m<91){
						
						n=m-65;
					}
		System.out.println("m "+m+" n "+n);
				pt_array[i][j]=n;
				x++;

				}
			        }
				 x=0;
				         n=0;
				         m=0;
					for(int j=0;j<y;j++){
						for(int i=0;i<key_order;i++){
							 m=122;
							if(x<pt.length()){
							m=ct.charAt(x);
							
								 }
								else
								m=122;	
								
						
						if(m>96&&m<123){
							
							n=m-97;
						}
			if(m>64&&m<91){
							
							n=m-65;
						}
			System.out.println("m "+m+" n "+n);
					ct_array[i][j]=n;
					x++;

					}
				        }
					for(int i=0;i<key_order;i++){
						for(int j=0;j<key_order;j++){
							pm[i][j]=pt_array[i][j];
							cm[i][j]=ct_array[i][j];

						}
					}
					
					try
					        {
						Matrix key=new Matrix(pm);
						System.out.println("det value is "+Matrix.determinant(key));
						int vals[]=gcd(mod(Matrix.determinant(key),b),b);
						int det_inv=mod(mod(vals[1],b),b);
						System.out.println("inverse is "+det_inv);
						       Matrix cof=new Matrix(key_order, key_order);
						cof=Matrix.cofactor(key);
						int[][] adj=new int[key_order][key_order]; 
							for(int j=0;j<key_order;j++){
								for(int i=0;i<key_order;i++){
								System.out.print("original "+cof.getValueAt(i, j)+" transpose "+mod(cof.getValueAt(i, j),b)+"\t");
								adj[j][i]=mod(cof.getValueAt(i, j),b);
							   }
								System.out.println("");
						       }
							int[][] inv=new int[key_order][key_order];
								for(int i=0;i<key_order;i++){
								for(int j=0;j<key_order;j++){
									inv[i][j]=mod(adj[i][j]*det_inv,b);
									System.out.print(""+inv[i][j]+"\t");
								   }
								System.out.println();
								   }
						
								int pd[][];
								pd=new int[key_order][key_order];
							int kd[][];
							kd=new int[key_order][key_order];
								int z;
								int sum=0;
								
									char decrpt = 0;
								for ( d = 0 ; d <key_order ; d++ ){
							for ( c = 0 ; c <key_order ; c++ )
							                {
							System.out.print("i: "+d+"j: "+c+" inv p: "+inv[d][c]+" c: "+cm[d][c]);
							                }
							                }
								for ( c = 0 ; c <key_order ; c++ )
							         {
							for ( d = 0 ; d <key_order ; d++ )
							            {   
							for ( int k1 = 0 ; k1 <key_order ; k1++ )
							               {
							sum = sum + cm[c][k1]*inv[k1][d];
							               }
							
							pd[c][d] = sum;
							sum = 0;
							            }
							         }
							System.out.println();
							System.out.println("decryption key matrix:-");
							
							for ( d = 0 ; d <key_order ; d++ ){
								for ( c = 0 ; c <key_order ; c++ )
								                {
							int mod=mod(pd[d][c],b);
							kd[d][c]=mod;

										System.out.print(kd[d][c]+"\t");
							
							                }
								System.out.println();
								}
						
					        }
					catch(ArithmeticException e)
					        {
					System.out.println(e);
					
					        }
					
		break;
		
	
	            }
	        }
	    }
		int numEqui(int x){
			return x;
			
		}
		static int mod(int a, int n){
			int mod=0;
			if(a>=0){			//To find mod for positive integer
				mod=a%n;
					}
			else{				//To find mod for negative integer
				mod=(n+(a%n))%n;
					}
			return mod;	
		}
		static int[] gcd(int p, int q) {
		if (q == 0)
		return new int[] { p, 1, 0 };

		int[] vals = gcd(q, p % q);
		int d = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		return new int[] { d, a, b };
		   }
		    }
	class Matrix {

	private int nrows;
	private int ncols;
	private int[][] data;
	public static int sum = 0;
	public Matrix(int[][] km) {
	this.data = km;
	this.nrows = km.length;
	this.ncols = km[0].length;
	        }

	public Matrix(int nrow, int ncol) {
	this.nrows = nrow;
	this.ncols = ncol;
	data = new int[nrow][ncol];
	        }
	public static Matrix transpose(Matrix matrix) {
	            Matrix transposedMatrix = new Matrix(matrix.getNcols(), matrix.getNrows());
	for (int i=0;i<matrix.getNrows();i++) {
	for (int j=0;j<matrix.getNcols();j++) {
	transposedMatrix.setValueAt(j, i, matrix.getValueAt(i, j));
	                }
	            }
	return transposedMatrix;
	        }
	public void setValueAt(int j, int i, int value) {
		data[j][i]=value;
				
			}

			public int getValueAt(int i, int j) {
				return data[i][j];
			}

			public int getNrows() {
				return this.nrows;
			}

			public int getNcols() {
				return this.ncols;
			}
			
			public static int determinant(Matrix matrix) {
	if (matrix.size() == 1) {
		return matrix.getValueAt(0, 0);
	            }
	if (matrix.size()==2) {
	return (matrix.getValueAt(0, 0) * matrix.getValueAt(1, 1)) - ( matrix.getValueAt(0, 1) * matrix.getValueAt(1, 0));
	            }
	
	for (int i=0; i<matrix.getNcols(); i++) {
	sum += changeSign(i) * matrix.getValueAt(0, i) * determinant(createSubMatrix(matrix, 0, i));
	System.out.println("Sum is "+i+"of"+matrix.getValueAt(0, i)+" determinant "+determinant(createSubMatrix(matrix, 0, i)));
	            }
	System.out.println("sum total "+sum);
	return sum;
	        }
	public static int changeSign(int i) {
				if(i%2==0){
			return 1;
		}
				return -1;
			}

			private int size() {
				return this.ncols;
			}

			public static Matrix createSubMatrix(Matrix matrix, int excluding_row, int excluding_col) {
	            Matrix mat = new Matrix(matrix.getNrows()-1, matrix.getNcols()-1);
	int r = -1;
	for (int i=0;i<matrix.getNrows();i++) {
	if (i==excluding_row)
	continue;
	r++;
	int c = -1;
	for (int j=0;j<matrix.getNcols();j++) {
	if (j==excluding_col)
	continue;
	mat.setValueAt(r, ++c, matrix.getValueAt(i, j));
	                }
	            }
	return mat;
	        } 
	public static Matrix cofactor(Matrix matrix)  {
	            Matrix mat = new Matrix(matrix.getNrows(), matrix.getNcols());
	for (int i=0;i<matrix.getNrows();i++) {
	for (int j=0; j<matrix.getNcols();j++) {
	mat.setValueAt(i, j, changeSign(i) * changeSign(j) * determinant(createSubMatrix(matrix, i, j)));
	                }
	            }
	
	return mat;
	        }
	
	    }
