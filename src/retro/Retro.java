package retro;

import edu.nju.cs.inform.core.diff.*;
import edu.nju.cs.inform.core.type.*;
import edu.nju.cs.inform.core.recommend.*;
import edu.nju.cs.inform.io.*;
import edu.nju.cs.inform.core.ir.*;

import java.util.*;

/**
 * created by yx 2018/8/26
 * ������Ŀdemo
 * Retro�㷨�����ļ�
 */

public class Retro {

	public String new_source_path;
	public String old_source_path;
	public String requirement_Path;
	public Set<CodeElementChange> codeElementChangeList;
	public List<Map.Entry<String, Double>> reqElementList;
	public Map<String, List<String>> recommendMethodsForRequirements;

	public void process(String new_source_path, String old_source_path, String requirement_Path) {
		CodeElementsComparer comparer;
        comparer = new CodeElementsComparer(new_source_path, old_source_path);
        comparer.diff();
        Set<CodeElementChange> codeElementChangeList = comparer.getCodeElementChangesList();
        this.codeElementChangeList = codeElementChangeList;
        // get change description from code changes
        ArtifactsCollection changeDescriptionCollection = comparer.getChangeDescriptionCollection();
        final ArtifactsCollection requirementCollection = ArtifactsReader.getCollections(requirement_Path, ".txt");

        // retrieval change description to requirement
        Retrieval retrieval = new Retrieval(changeDescriptionCollection, requirementCollection, IRModelConst.VSM);
        retrieval.tracing();
        final SimilarityMatrix similarityMatrix = retrieval.getSimilarityMatrix();
        final MethodRecommendation methodRecommendation = new MethodRecommendation(comparer, requirementCollection, similarityMatrix);
        final Map<String, List<String>> recommendMethodsForRequirements = methodRecommendation.getRecommendMethodsForRequirements();
        this.recommendMethodsForRequirements = recommendMethodsForRequirements;

        Map<String, Double> candidatedOutdatedRequirementsRank = retrieval.getCandidateOutdatedRequirementsRank();
        //��mapת����list
        List<Map.Entry<String, Double>> list = new ArrayList<>(candidatedOutdatedRequirementsRank.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            //��������
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        this.reqElementList = list;
	}


	//��Ԫ�������
	public static void main(String[] args) {
		String old_source_path = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Change3";
		String new_source_path = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Change4";
		String requirement_Path = "/Users/mac/Desktop/interfaceDemo/data/sample/AquaLush_Requirement";
		Retro re = new Retro();
		re.processTest(new_source_path, old_source_path, requirement_Path);
		System.out.println("-----------------���������ı����к����Ƽ�-----------------");
        //������������
        //��ȡ��requirementElementsTable�б������һ�е�id--eg:SRS358
		String req = null;
		do {
	        System.out.print("���������ı����(��ʽ��SRSxxx)������q�˳���");
	        Scanner scan = new Scanner(System.in);
	        req = scan.nextLine();
	        System.out.println("�����ı���" + req);

	        System.out.println("-----------------methods recommendation-----------------");

	        	List<String> recommendList = re.recommendMethodsForRequirements.get(req);
	            int index = 1;
	            for (String method : recommendList) {
	                System.out.println(index + ": " + method);
	                ++index;
	           }
        }while(req != "q");
	}

	//������㷨
	public void processTest(String new_source_path, String old_source_path, String requirement_Path) {
        CodeElementsComparer comparer;
        System.out.println("-----------------Change Regions-----------------");
        comparer = new CodeElementsComparer(new_source_path, old_source_path);
        comparer.diff();
        Set<CodeElementChange> codeElementChangeList = comparer.getCodeElementChangesList();
        this.codeElementChangeList = codeElementChangeList;
        System.out.println("-----------------Code Elements Diff-----------------");
        for (CodeElementChange elementChange : codeElementChangeList) {
            System.out.println(elementChange.getElementName() + " " + elementChange.getElementType() + " " + elementChange.getChangeType());
        }
        int reqDisplayNum = 30;
        System.out.println("-----------------Top" + reqDisplayNum + " Requirement Elements-----------------");

        // get change description from code changes
        ArtifactsCollection changeDescriptionCollection = comparer.getChangeDescriptionCollection();
        final ArtifactsCollection requirementCollection = ArtifactsReader.getCollections(requirement_Path, ".txt");

        // retrieval change description to requirement
        Retrieval retrieval = new Retrieval(changeDescriptionCollection, requirementCollection, IRModelConst.VSM);
        retrieval.tracing();
        final SimilarityMatrix similarityMatrix = retrieval.getSimilarityMatrix();
        final MethodRecommendation methodRecommendation = new MethodRecommendation(comparer, requirementCollection, similarityMatrix);
        final Map<String, List<String>> recommendMethodsForRequirements = methodRecommendation.getRecommendMethodsForRequirements();
        this.recommendMethodsForRequirements = recommendMethodsForRequirements;

        Map<String, Double> candidatedOutdatedRequirementsRank = retrieval.getCandidateOutdatedRequirementsRank();
        //��mapת����list
        List<Map.Entry<String, Double>> list = new ArrayList<>(candidatedOutdatedRequirementsRank.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            //��������
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        this.reqElementList = list;

        int index = 0;
        for (Map.Entry<String, Double> map : list) {
            if(index < reqDisplayNum) {
                System.out.println(map.getKey() + "  " + String.valueOf(map.getValue()));
                index++;
            }
            else{
                break;
            }
        }
    }
}
