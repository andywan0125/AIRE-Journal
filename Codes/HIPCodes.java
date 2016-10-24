	public static void HIP(HashMap<String, String[]> allPath, String[][] trainingDataset, String[][] testDataset) throws IOException{
		
        HashMap<String, String> hmStatus = new HashMap<String, String>();
        for(int i=1; i<trainingDataset.length-1; i++){
      	  hmStatus.put(trainingDataset[i][0], "Selected");
        }
        
        HashMap<String, String[]> hmAncestors = new HashMap<String, String[]>();
        HashMap<String, String[]> hmDescendants = new HashMap<String, String[]>();
        ArrayList<String> arrayListAncestors= new ArrayList<String>();
        ArrayList<String> arrayListDescendants= new ArrayList<String>();
        
        for(int kkk=1; kkk<trainingDataset.length-1; kkk++){
        	Set<Entry<String,String[]>> setpathForAllBPT5Selecting=allPath.entrySet();
	        Iterator<Entry<String, String[]>> itersetpathForAllBPT5Selecting = setpathForAllBPT5Selecting.iterator();
	        while(itersetpathForAllBPT5Selecting.hasNext()){
	        	Entry<String, String[]> entryitersetpathForAllBPT5Selecting = itersetpathForAllBPT5Selecting.next();
	            for(int jjj=0; jjj<entryitersetpathForAllBPT5Selecting.getValue().length; jjj++){
	            	if(trainingDataset[kkk][0].equals(entryitersetpathForAllBPT5Selecting.getValue()[jjj])){
	            		for(int ppp=0; ppp<jjj; ppp++){
	            			arrayListAncestors.add(entryitersetpathForAllBPT5Selecting.getValue()[ppp]);
	            		}
	            		for(int ooo=entryitersetpathForAllBPT5Selecting.getValue().length-1; ooo>jjj; ooo--){
	            			arrayListDescendants.add(entryitersetpathForAllBPT5Selecting.getValue()[ooo]);
	            		}
	            	}
	            }
	        }
        	
	        String[]  arrayAncestorsTemp = new String[arrayListAncestors.size()];
	        arrayAncestorsTemp = arrayListAncestors.toArray(arrayAncestorsTemp);
	        for(int rrr=0; rrr<arrayAncestorsTemp.length; rrr++){
	        	if(!arrayAncestorsTemp[rrr].equals("D")){
	        	   for(int kjk=0; kjk<arrayAncestorsTemp.length; kjk++){
	        		   if(rrr!=kjk){
	        			  if(arrayAncestorsTemp[rrr].equals(arrayAncestorsTemp[kjk])){
	        				 arrayAncestorsTemp[kjk]="D";
	        			  }
	        		  }
	        	   }
	        	}
	        }
	        ArrayList<String> arrayListAncestorsNoDuplicated= new ArrayList<String>();
	        for(int sss=0; sss<arrayAncestorsTemp.length; sss++){
	        	if(!arrayAncestorsTemp[sss].equals("D")){
	        		arrayListAncestorsNoDuplicated.add(arrayAncestorsTemp[sss]);
	        	}
	        }
	        String[]  arrayAncestors = new String[arrayListAncestorsNoDuplicated.size()];
	        arrayAncestors = arrayListAncestorsNoDuplicated.toArray(arrayAncestors);
	        
	        hmAncestors.put(trainingDataset[kkk][0], arrayAncestors);
	        arrayListAncestorsNoDuplicated.clear();
	        arrayListAncestors.clear();
	        //-------
	        
	        String[]  arrayDescendantsTemp = new String[arrayListDescendants.size()];
	        arrayDescendantsTemp = arrayListDescendants.toArray(arrayDescendantsTemp);
	        for(int rrr=0; rrr<arrayDescendantsTemp.length; rrr++){
	        	if(!arrayDescendantsTemp[rrr].equals("D")){
	        	   for(int kjk=0; kjk<arrayDescendantsTemp.length; kjk++){
	        		   if(rrr!=kjk){
	        			  if(arrayDescendantsTemp[rrr].equals(arrayDescendantsTemp[kjk])){
	        				  arrayDescendantsTemp[kjk]="D";
	        			  }
	        		  }
	        	   }
	        	}
	        }
	        ArrayList<String> arrayListDescendantsNoDuplicated= new ArrayList<String>();
	        for(int sss=0; sss<arrayDescendantsTemp.length; sss++){
	        	if(!arrayDescendantsTemp[sss].equals("D")){
	        		arrayListDescendantsNoDuplicated.add(arrayDescendantsTemp[sss]);
	        	}
	        }
	        String[]  arrayDescendants = new String[arrayListDescendantsNoDuplicated.size()];
	        arrayDescendants = arrayListDescendantsNoDuplicated.toArray(arrayDescendants);
	        
	        hmDescendants.put(trainingDataset[kkk][0], arrayDescendants);
	        arrayListDescendantsNoDuplicated.clear();
	        arrayListDescendants.clear();
        	
        }
        
        for(int column=1; column<testDataset[0].length; column++){
            for(int i=0; i<testDataset.length-1; i++){
            	if(testDataset[i][column].equals("1")){
            		if(hmAncestors.get(testDataset[i][0]).length>0){
            			for(int asd=0; asd<hmAncestors.get(testDataset[i][0]).length; asd++){
            				hmStatus.put(hmAncestors.get(testDataset[i][0])[asd], "Removed");
            			}
            		}
            	}else{
            		if(hmDescendants.get(testDataset[i][0]).length>0){
            			for(int hjk=0; hjk<hmDescendants.get(testDataset[i][0]).length; hjk++){
            				hmStatus.put(hmDescendants.get(testDataset[i][0])[hjk], "Removed");
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
