    public static String BubbleSortBackward(String[] Attributes, HashMap<String, String> hmRelevanceMeasurement){
		
		String maxRele=null;
		HashMap<String, String> hmPositionSameMaxRele = new HashMap<String, String>();
        String[][] Relevance = new String[Attributes.length][2]; 
        
        for(int i=0; i<Attributes.length; i++){
        	Relevance[i][0]=Attributes[i];
        	Relevance[i][1]=hmRelevanceMeasurement.get(Attributes[i]);
        	hmPositionSameMaxRele.put(Attributes[i], Integer.toString(i));        
        }
        
        if(Relevance.length!=1){
           boolean markForChange2=true;
           String tempGONameRelevance=Relevance[0][0];
           String tempRelevanceValue=Relevance[0][1];
        
           while(markForChange2){
           	   markForChange2=false;
        	   for(int i=1; i<Relevance.length; i++){
        		   if(Double.parseDouble(tempRelevanceValue)<Double.parseDouble(Relevance[i][1])){       			
        			   tempGONameRelevance=Relevance[i][0];
        			   tempRelevanceValue=Relevance[i][1];	   
        			   markForChange2=true;
        		   }
        	   }
           }
           
           ArrayList<String> arrayListSameMaxRele= new ArrayList<String>();
           arrayListSameMaxRele.add(tempGONameRelevance);
           
           for(int s=0; s<Relevance.length; s++){
        	   if(tempRelevanceValue.equals(Relevance[s][1])&&!tempGONameRelevance.equals(Relevance[s][0])){
        		  arrayListSameMaxRele.add(Relevance[s][0]);
        	   }
           }
           
           if(arrayListSameMaxRele.size()>1){
        	  maxRele=arrayListSameMaxRele.get(0);
        	  for(int jjj=0; jjj<arrayListSameMaxRele.size(); jjj++){
        		  if(Double.parseDouble(hmPositionSameMaxRele.get(maxRele))<Double.parseDouble(hmPositionSameMaxRele.get(arrayListSameMaxRele.get(jjj)))){
        			  maxRele=arrayListSameMaxRele.get(jjj);
        		  }
        	  }
           }else{
        	  maxRele=arrayListSameMaxRele.get(0); 
           }
           arrayListSameMaxRele.clear();
	    }else{
	    	maxRele=Relevance[0][0];
	    }
		  
		return maxRele;
	}