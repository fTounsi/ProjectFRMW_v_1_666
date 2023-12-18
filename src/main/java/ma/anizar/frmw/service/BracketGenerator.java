package ma.anizar.frmw.service;

import ma.anizar.frmw.dto.BracketNode;
import ma.anizar.frmw.model.Member;

import java.util.Collections;
import java.util.List;

public class BracketGenerator {

    private BracketNode root;

    public BracketGenerator(List<Member> participants) {
        Collections.shuffle(participants);
        root = buildBracketTree(participants, 0, participants.size() - 1);
    }

    private BracketNode buildBracketTree(List<Member> participants, int start, int end) {
        if (start > end) return null;

        // Middle item becomes root
        int mid = (start + end) / 2;
        BracketNode node = new BracketNode(participants.get(mid));

        node.setLeft(buildBracketTree(participants, start, mid - 1));
        node.setRight(buildBracketTree(participants, mid + 1, end));

        return node;
    }

    public void printBracket() {
        printBracketTree(root, 0);
    }

    private void printBracketTree(BracketNode node, int depth) {
        if (node == null) return;

        printBracketTree(node.getRight(), depth + 1);
        System.out.println(" ".repeat(depth * 4) + node.getParticipant().getFirstName());
        printBracketTree(node.getLeft(), depth + 1);
    }
}
