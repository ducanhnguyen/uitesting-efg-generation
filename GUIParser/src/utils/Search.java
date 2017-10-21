package utils;

import guitreeparser.object.INode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ducanhnguyen
 */
public class Search {

    /**
     * @param root Root sub tree
     * @param condition Điều kiện tìm kiếm
     * @return Danh sách node thỏa mãn điều kiện tìm kiếm
     */
    public static List<INode> searchNodes(INode root,
            ISearchCondition condition) {
        List<INode> output = new ArrayList<>();

        for (INode child : root.getChidren()) {
            if (condition.isSatisfiable(child)) {
                output.add(child);
            }
            output.addAll(Search.searchNodes(child, condition));
        }
        return output;
    }
}
