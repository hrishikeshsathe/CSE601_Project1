package com.dm.query;

public class Queries {
	public static String query2_6_aml_patients = "SELECT diag.P_ID, mrna.EXP FROM MICROARRAY_FACT mrna, SAMPLE samp, CLINICAL_FACT clinfact, DIAGNOSIS diag WHERE mrna.S_ID = samp.S_ID AND samp.S_ID = clinfact.S_ID AND clinfact.P_ID = diag.P_ID AND diag.DS_ID IN (SELECT DS_ID FROM DISEASE WHERE NAME = 'AML' ) AND mrna.PB_ID IN (SELECT prob.PB_ID FROM PROBE prob, GENE_FACT genefact WHERE genefact.GO_ID = '0007154' AND genefact.U_ID = prob.U_ID )";
	public static String query2_6_all_patients = "SELECT diag.P_ID, mrna.EXP FROM MICROARRAY_FACT mrna, SAMPLE samp, CLINICAL_FACT clinfact, DIAGNOSIS diag WHERE mrna.S_ID = samp.S_ID AND samp.S_ID = clinfact.S_ID AND clinfact.P_ID = diag.P_ID AND diag.DS_ID IN (SELECT DS_ID FROM DISEASE WHERE NAME = 'ALL' ) AND mrna.PB_ID IN (SELECT prob.PB_ID FROM PROBE prob, GENE_FACT genefact WHERE genefact.GO_ID = '0007154' AND genefact.U_ID = prob.U_ID )";
}
