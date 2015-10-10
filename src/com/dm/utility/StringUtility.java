package com.dm.utility;

public class StringUtility {

	public static final String DISEASE_NAME = "diseaseName";
	public static final String DISEASE_TYPE = "diseaseType";
	public static final String DISEASE_DESCRIPTION = "diseaseDescription";
	public static final String MEASURE_UNIT_ID = "measureUnitID";
	public static final String CLUSTER_ID = "clusterID";
	public static final String GO_ID = "goID";
	public static final String DISEASE_ONE = "diseaseOne";
	public static final String DISEASE_TWO = "diseaseTwo";
	public static final String RECALCULATE_INFORMATIVE_GENES = "recalculateInformativeGenes";
	
	
	public static final String QUERY_1 = "select d.name,count(dg.p_id) from diagnosis dg "
			+ "inner join disease d on (dg.ds_id = d.ds_id) where d.name = ? group by d.name "
			+ "Union "
			+ "select d.type,count(dg.p_id) from diagnosis dg "
			+ "inner join disease d on (dg.ds_id = d.ds_id) where d.type = ? group by d.type "
			+ "Union "
			+ "select d.description ,count(dg.p_id) from diagnosis dg "
			+ "inner join disease d on (dg.ds_id = d.ds_id) where d.description = ? group by d.description";
	
	public static final String QUERY_2 = "select distinct type from drug where DR_ID in "
			+ "(select du.dr_id from drug_use du where du.P_ID in (select distinct dg.p_id from diagnosis dg, "
			+ "disease d where dg.ds_ID = d.DS_ID and d.DESCRIPTION=?))";
	
	public static final String QUERY_3 = "select exp from microarray_fact where s_id in "
			+ "(select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name = ?)) and s_id is not null) and "
			+ "pb_id in (select pb_id from probe where U_ID in "
			+ "(select u_id from gene_cluster where cl_id = ?)) and mu_id = ?";
	
	public static final String QUERY_4_A = "select exp from microarray_fact where pb_id in "
			+ "( select pb_id from probe where u_id in "
			+ "(select u_id from go_annotation where go_id = ?)) and "
			+ "s_id in (select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name = ?)) and s_id is not null)";
	
	public static final String QUERY_4_B = "select exp from microarray_fact where pb_id in "
			+ "( select pb_id from probe where u_id in "
			+ "(select u_id from go_annotation where go_id = ?)) and "
			+ "s_id in (select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name <> ?)) and s_id is not null)";
	
	public static final String QUERY_5 = "select exp from microarray_fact where pb_id in "
			+ "( select pb_id from probe where u_id in "
			+ "(select u_id from go_annotation where go_id = ?)) and "
			+ "s_id in (select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name = ?)) and s_id is not null)";
	
	public static String QUERY_6_A = "SELECT diag.P_ID, mrna.EXP FROM MICROARRAY_FACT mrna, SAMPLE samp, CLINICAL_SAMPLE clinsamp, DIAGNOSIS diag "
			+ "WHERE mrna.S_ID = samp.S_ID AND samp.S_ID = clinsamp.S_ID AND clinsamp.P_ID = diag.P_ID AND diag.DS_ID IN "
			+ "(SELECT DS_ID FROM DISEASE WHERE NAME = ? ) AND mrna.PB_ID IN "
			+ "(SELECT prob.PB_ID FROM PROBE prob, GENE_FACT genefact WHERE genefact.GO_ID = ? AND genefact.U_ID = prob.U_ID )";
	
	public static String QUERY_6_B = "SELECT diag.P_ID, mrna.EXP FROM MICROARRAY_FACT mrna, SAMPLE samp, CLINICAL_SAMPLE clinsamp, DIAGNOSIS diag "
			+ "WHERE mrna.S_ID = samp.S_ID AND samp.S_ID = clinsamp.S_ID AND clinsamp.P_ID = diag.P_ID AND diag.DS_ID IN "
			+ "(SELECT DS_ID FROM DISEASE WHERE NAME = ? ) AND mrna.PB_ID IN "
			+ "(SELECT prob.PB_ID FROM PROBE prob, GENE_FACT genefact WHERE genefact.GO_ID = ? AND genefact.U_ID = prob.U_ID )";
	
	public static final String PART_3_A_1 = "select g.u_id, mf.exp from microarray_fact mf "
			+ "inner join probe p on mf.pb_id = p.pb_id "
			+ "inner join gene g on p.u_id = g.u_id where s_id in "
			+ "(select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name = ?)) and s_id is not null) order by g.u_id";
	
	public static final String PART_3_A_2 = "select g.u_id, mf.exp from microarray_fact mf "
			+ "inner join probe p on mf.pb_id = p.pb_id "
			+ "inner join gene g on p.u_id = g.u_id where s_id in "
			+ "(select s_id from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name != ?)) and s_id is not null) order by g.u_id";
	
	public static final String PART_3_B_1 = "select cs.p_id,g.u_id,mf.exp from microarray_fact mf "
			+ "inner join "
			+ "probe p on mf.pb_id = p.pb_id "
			+ "inner join "
			+ "gene g on p.u_id = g.u_id "
			+ "inner join "
			+ "(select * from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name = 'ALL')) "
			+ "and s_id is not null) cs on mf.s_id=cs.s_id  where g.u_id in ? order by cs.p_id, g.u_id";
	
	public static final String PART_3_B_2 = "select cs.p_id,g.u_id,mf.exp from microarray_fact mf "
			+ "inner join "
			+ "probe p on mf.pb_id = p.pb_id "
			+ "inner join "
			+ "gene g on p.u_id = g.u_id "
			+ "inner join "
			+ "(select * from clinical_sample where p_id in "
			+ "(select distinct p_id from diagnosis where ds_id in "
			+ "(select ds_id from disease where name != 'ALL')) "
			+ "and s_id is not null) cs on mf.s_id=cs.s_id  where g.u_id in ? order by cs.p_id, g.u_id";
	
	public static final String PART_3_B_3 = "select * from test_samples where u_id in ? order by u_id";
	
}
