	public static void MR(HashMap<String, String[]> allPath, String[][] trainingDataset, String[][] testDataset) throws IOException{
			
		String[][] MeasurementResults = MeasurementResults(trainingDataset);
		HashMap<String, String> hmRelevanceMeasurement = new HashMap<String, String>();

        for(int i=1; i<MeasurementResults.length; i++){
        	hmRelevanceMeasurement.put(MeasurementResults[i][0], MeasurementResults[i][5]);
        }
        
        HashMap<String, String> hmStatus = new HashMap<String, String>();
        for(int i=1; i<MeasurementResults.length; i++){
      	  hmStatus.put(MeasurementResults[i][0], "Selected");
        }
        
        ArrayList<String> ForwardList= new ArrayList<String>();
        ArrayList<String> BackwardList= new ArrayList<String>();
        
        for(int column=1; column<testDataset[0].length; column++){
            for(int i=0; i<testDataset.length-1; i++){
      			  if(testDataset[i][column].equals("1")){
      		        Set<Entry<String,String[]>> setpathForAllBPT5Selecting=allPath.entrySet();
      		        Iterator<Entry<String, String[]>> itersetpathForAllBPT5Selecting = setpathForAllBPT5Selecting.iterator();
      		        while(itersetpathForAllBPT5Selecting.hasNext()){
      		        	Entry<String, String[]> entryitersetpathForAllBPT5Selecting = itersetpathForAllBPT5Selecting.next();
      		            for(int yyy=0; yyy<entryitersetpathForAllBPT5Selecting.getValue().length; yyy++){
      		            	if(entryitersetpathForAllBPT5Selecting.getValue()[yyy].equals(testDataset[i][0])){
      		            		for(int eee=0; eee<yyy+1; eee++){
      		            			ForwardList.add(entryitersetpathForAllBPT5Selecting.getValue()[eee]);
      		            		}
      		            		String[] ForwardArray;
      		            		ForwardArray = new String[ForwardList.size()];
      		            		ForwardArray = ForwardList.toArray(ForwardArray);
      		            		ForwardList.clear();
      		            		String MaxFord=BubbleSortForward(ForwardArray,hmRelevanceMeasurement);
      		            		for(int vcl=0; vcl<ForwardArray.length; vcl++){
      		            			if(!ForwardArray[vcl].equals(MaxFord)){
      		            				hmStatus.put(ForwardArray[vcl], "Removed");
      		            			}
      		            		}
      		            	}
      		            }
      		        }
      			  }else{
      				Set<Entry<String,String[]>> setpathForAllBPT5Selecting2=allPath.entrySet();
        		        Iterator<Entry<String, String[]>> itersetpathForAllBPT5Selecting2 = setpathForAllBPT5Selecting2.iterator();  
        		        while(itersetpathForAllBPT5Selecting2.hasNext()){
        		        	Entry<String, String[]> entryitersetpathForAllBPT5Selecting2 = itersetpathForAllBPT5Selecting2.next();
        		        	for(int rrr=0; rrr<entryitersetpathForAllBPT5Selecting2.getValue().length; rrr++){
        		        		if(entryitersetpathForAllBPT5Selecting2.getValue()[rrr].equals(testDataset[i][0])){
        		        			for(int www=entryitersetpathForAllBPT5Selecting2.getValue().length-1; www>rrr-1; www--){
        		        				BackwardList.add(entryitersetpathForAllBPT5Selecting2.getValue()[www]);
        		        			}
        		        			String[] BackwardArray;
    		        				BackwardArray = new String[BackwardList.size()];
    		        				BackwardArray = BackwardList.toArray(BackwardArray);
    		        				BackwardList.clear();
    		        				String MaxBack=BubbleSortBackward(BackwardArray,hmRelevanceMeasurement);
          		            		for(int jcl=0; jcl<BackwardArray.length; jcl++){
          		            			if(!BackwardArray[jcl].equals(MaxBack)){
          		            				hmStatus.put(BackwardArray[jcl], "Removed");
          		            			}
          		            		}
        		        		}
        		        	}
        		        }
      			  }
             }
        
        
         ArrayList<String> arrayListSelectedGOTermsForC= new ArrayList<String>();
         Set<Entry<String,String>> sethmStatus333=hmStatus.entrySet();
         Iterator<Entry<String, String>> itersethmStatus333 = sethmStatus333.iterator();
         while(itersethmStatus333.hasNext()){
       	  Entry<String, String> entryitersethmStatus333 = itersethmStatus333.next();
       	  if(entryitersethmStatus333.getValue().equals("Selected")){
       		  arrayListSelectedGOTermsForC.add(entryitersethmStatus333.getKey());
       	  }
         }
         
         Set<Entry<String,String>> sethmStatus=hmStatus.entrySet();
         Iterator<Entry<String, String>> itersethmStatus = sethmStatus.iterator();
         while(itersethmStatus.hasNext()){
        	 Entry<String, String> entryitersethmStatus = itersethmStatus.next();
        	 hmStatus.put(entryitersethmStatus.getKey(), "Selected");
         }
         
      }		
	}
