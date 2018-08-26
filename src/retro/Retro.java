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
	
	//��Ԫ�������
	public static void main(String[] args) {
		String new_source_path = "data/sample/AquaLush_Change4";
		String old_source_path = "data/sample/AquaLush_Change3";
		String requirement_Path = "data/sample/AquaLush_Requirement";
		Retro.listComparer(new_source_path, old_source_path, requirement_Path);
	}
	
	//������㷨
	public static void listComparer(String new_source_path, String old_source_path, String requirement_Path) {
        CodeElementsComparer comparer;
        System.out.println("-----------------Change Regions-----------------");
        comparer = new CodeElementsComparer(new_source_path, old_source_path);
        comparer.diff();
        Set<CodeElementChange> codeElementChangeList = comparer.getCodeElementChangesList();
        System.out.println("-----------------Code Elements Diff-----------------");
        for (CodeElementChange elementChange : codeElementChangeList) {
            System.out.println(elementChange.getElementName() + " " + elementChange.getElementType() + " " + elementChange.getChangeType());
        }
        int reqDisplayNum = 100;
        System.out.println("-----------------Top" + reqDisplayNum + " Requirement Elements-----------------");

        // get change description from code changes
        ArtifactsCollection changeDescriptionCollection = comparer.getChangeDescriptionCollection();
        final ArtifactsCollection requirementCollection = ArtifactsReader.getCollections(requirement_Path, ".txt");

        // retrieval change description to requirement
        Retrieval retrieval = new Retrieval(changeDescriptionCollection, requirementCollection, IRModelConst.VSM);
        retrieval.tracing();
        final SimilarityMatrix similarityMatrix = retrieval.getSimilarityMatrix();
        final MethodRecommendation methodRecommendation = new MethodRecommendation(comparer, requirementCollection, similarityMatrix);
        final Map<String, java.util.List<String>> recommendMethodsForRequirements = methodRecommendation.getRecommendMethodsForRequirements();

        Map<String, Double> candidatedOutdatedRequirementsRank = retrieval.getCandidateOutdatedRequirementsRank();
        //��mapת����list
        java.util.List<Map.Entry<String, Double>> list = new ArrayList<>(candidatedOutdatedRequirementsRank.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            //��������
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

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
        
        
        System.out.println("-----------------���������ı����к����Ƽ�-----------------");
        //������������
        //��ȡ��requirementElementsTable�б������һ�е�id--eg:SRS358
        
        System.out.print("���������ı����(��ʽ��SRSxxx)��");
        Scanner scan = new Scanner(System.in);
        String req = scan.nextLine();
        System.out.println("�����ı���" + req);
        
        System.out.println("-----------------methods recommendation-----------------");
        
        List<String> recommendList = recommendMethodsForRequirements.get(req);
        index = 0;
        for (String method : recommendList) {
            System.out.println(index + ": " + method);
            ++index;
       }
    }
}
