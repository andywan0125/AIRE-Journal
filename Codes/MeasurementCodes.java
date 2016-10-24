	public static String[][] MeasurementResults(String[][] trainingDataset) throws IOException{

		String[][] matrixResult = new String[trainingDataset.length+1][6];
		matrixResult[0][0]="GO";
		matrixResult[0][1]="P(Class=Pro|GO=Yes)";
		matrixResult[0][2]="P(Class=Pro|GO=No)";
		matrixResult[0][3]="P(Class=Anti|GO=Yes)";
		matrixResult[0][4]="P(Class=Anti|GO=No)";
		matrixResult[0][5]="Diff";

		
		for(int i=0; i<trainingDataset.length; i++){
			matrixResult[i+1][0]=trainingDataset[i][0];
		}
		
		//------------------------------------Calculate p(Class | GO=Yes)--------------------------------------
				ArrayList<String> arrayListMiddleColumnIndex = new ArrayList<String>();
				for(int i=1; i<trainingDataset.length; i++){
					String GOName=matrixResult[i][0];
					for(int rowIndexForRealMatrix=0; rowIndexForRealMatrix<trainingDataset.length; rowIndexForRealMatrix++){
						if(GOName.equals(trainingDataset[rowIndexForRealMatrix][0])){
							for(int columnIndexRealMatrix=1; columnIndexRealMatrix<trainingDataset[0].length; columnIndexRealMatrix++){
								if(trainingDataset[rowIndexForRealMatrix][columnIndexRealMatrix].equals("1")){
									arrayListMiddleColumnIndex.add(Integer.toString(columnIndexRealMatrix));
								}
							}
							String[] middleMatrix=new String[arrayListMiddleColumnIndex.size()+1];
							middleMatrix[0]="Class";
							int columnIndexForMid=1;
							for(int indexArrayListMid=0; indexArrayListMid<arrayListMiddleColumnIndex.size(); indexArrayListMid++){
								middleMatrix[columnIndexForMid]=trainingDataset[trainingDataset.length-1][Integer.parseInt(arrayListMiddleColumnIndex.get(indexArrayListMid))];
								columnIndexForMid++;
							}
							int countForYes=0;
							for(int columnMidMatrix=1; columnMidMatrix<middleMatrix.length; columnMidMatrix++){
								if(middleMatrix[columnMidMatrix].equals("1")){
									countForYes++;
								}
							}
							double ProYes=(double)(countForYes+1)/(double)(middleMatrix.length+1);   //Laplacian Correction
							double ProNo=1-ProYes;
							matrixResult[i][1]=Double.toString(ProYes);
							matrixResult[i][3]=Double.toString(ProNo);
							arrayListMiddleColumnIndex.clear();
							break;
						}
					}
				}
				
				//------------------------------------Calculate p(Class | GO=No)--------------------------------------
				ArrayList<String> arrayListMiddleColumnIndex2 = new ArrayList<String>();
				for(int i=1; i<trainingDataset.length; i++){
					String GOName=matrixResult[i][0];
					for(int rowIndexForRealMatrix=0; rowIndexForRealMatrix<trainingDataset.length; rowIndexForRealMatrix++){
						if(GOName.equals(trainingDataset[rowIndexForRealMatrix][0])){
							for(int columnIndexRealMatrix=1; columnIndexRealMatrix<trainingDataset[0].length; columnIndexRealMatrix++){
								if(trainingDataset[rowIndexForRealMatrix][columnIndexRealMatrix].equals("0")){
									arrayListMiddleColumnIndex2.add(Integer.toString(columnIndexRealMatrix));
								}
							}
							String[] middleMatrix=new String[arrayListMiddleColumnIndex2.size()+1];
							middleMatrix[0]="Class";
							int columnIndexForMid=1;
							for(int indexArrayListMid=0; indexArrayListMid<arrayListMiddleColumnIndex2.size(); indexArrayListMid++){
								middleMatrix[columnIndexForMid]=trainingDataset[trainingDataset.length-1][Integer.parseInt(arrayListMiddleColumnIndex2.get(indexArrayListMid))];
								columnIndexForMid++;
							}
							int countForYes=0;
							for(int columnMidMatrix=1; columnMidMatrix<middleMatrix.length; columnMidMatrix++){
								if(middleMatrix[columnMidMatrix].equals("1")){
									countForYes++;
								}
							}
							double ProYes=(double)(countForYes+1)/(double)(middleMatrix.length+1);
							double ProNo=1-ProYes;
							matrixResult[i][2]=Double.toString(ProYes);
							matrixResult[i][4]=Double.toString(ProNo);
							arrayListMiddleColumnIndex2.clear();
							break;
						}
					}
				}
				
				//------------------------Calculate Difference----------------------
				for(int row=1; row<matrixResult.length-1; row++){
					matrixResult[row][5]=Double.toString(
							(Double.parseDouble(matrixResult[row][1])-Double.parseDouble(matrixResult[row][2]))*(Double.parseDouble(matrixResult[row][1])-Double.parseDouble(matrixResult[row][2]))+
							(Double.parseDouble(matrixResult[row][3])-Double.parseDouble(matrixResult[row][4]))*(Double.parseDouble(matrixResult[row][3])-Double.parseDouble(matrixResult[row][4]))
							);
				}
						        		
		return matrixResult;		        
	}