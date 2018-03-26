# ICLP2018
## A Trajectory Calculus for Qualitative Spatial Reasoning Using Answer Set Programming
### Supplementary Material


**encodings**
* TC10-coi7.asp:	          COI7 encoding for TC-10
* TC10-ctsa.asp:	          CTSA encoding for TC-10
* TC10-ctsa2.asp: 	          CTSA2 encoding for TC-10
* TC10-gen.asp: 	          GEN encoding for TC-10
* TC6-coi7.asp: 	          COI7 encoding for TC-6
* TC6-ctsa.asp: 	          CTSA encoding for TC-6
* TC6-ctsa2.asp: 	          CTSA2 encoding for TC-6
* TC6-gen.asp: 	          GEN encoding for TC-6
* coi7-ctsa-input.asp: 	  Input encoding using COI7 or CTSA
* ctsa2-input.asp: 	          Input encoding using CTSA2
* gen-input.asp:               Input encoding using GEN
  
**CSV2ASPConverter.java**:             Java code used for the experiments, that converts the CSV dataset into ASP programs 

**Sampled_Trajectory_latlng.csv**:     Raw version of the dataset used in the experiments, in the form of latitude/longitude pairs

**Sampled_Trajectory_region.csv**:     Dataset as used in the experiments, in the form of sequences of numbered regions
* Mean length = 282 regions with standard deviation 33.27
* 39.8% of trajectories start and end at the same region
