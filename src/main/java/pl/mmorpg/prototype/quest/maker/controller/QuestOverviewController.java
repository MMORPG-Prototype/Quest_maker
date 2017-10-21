package pl.mmorpg.prototype.quest.maker.controller;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pl.mmorpg.prototype.quest.maker.model.QuestTaskFXContainer;
import pl.mmorpg.prototype.quest.maker.modelfx.FieldEditableTreeCell;
import pl.mmorpg.prototype.server.quests.QuestTask;

public class QuestOverviewController
{
	@FXML
	private TreeView<String> treeQuestView;

	@FXML
	private ComboBox<QuestTaskFXContainer> questTypeList;

	@FXML
	private AnchorPane questPropertyControlsContainer;

	public void initialize()
	{
		initializeComboBox();
		initializeDynamicControls();
		setTreeQuestViewCellFactory();
		treeQuestView.setRoot(new TreeItem<String>("Quest root task"));
		treeQuestView.setEditable(true);
	}

	private void initializeDynamicControls()
	{
		QuestTaskFXContainer selectedItem = questTypeList.getSelectionModel().getSelectedItem();
		Collection<Control> controls = selectedItem.produceControls();
		questPropertyControlsContainer.getChildren().clear();
		questPropertyControlsContainer.getChildren()
				.addAll(controls.stream().map( q -> {
					HBox a = new HBox();
					a.setLayoutX(100*new Random().nextInt(5));
					a.setLayoutY(100*new Random().nextInt(5));
					a.getChildren().add(q);
					a.autosize();
					return a;
				}).collect(Collectors.toList()));
		questPropertyControlsContainer.getChildren().forEach(n -> n.autosize());
		questPropertyControlsContainer.autosize();
	}

	private void initializeComboBox()
	{
		Collection<QuestTaskFXContainer> questTaskTypes = reflectiveScan();
		questTypeList.getItems().addAll(questTaskTypes);
		questTypeList.getSelectionModel().select(0);
	}

	private Collection<QuestTaskFXContainer> reflectiveScan()
	{
		Reflections reflection = new Reflections(QuestTask.class.getPackage().getName());
		Set<Class<? extends QuestTask>> questTasks = reflection.getSubTypesOf(QuestTask.class);
		questTasks.removeIf(type -> Modifier.isAbstract(type.getModifiers()));

		Collection<QuestTaskFXContainer> questTaskContainers = questTasks.stream().map(QuestTaskFXContainer::new)
				.collect(Collectors.toList());
		return questTaskContainers;
	}

	private void setTreeQuestViewCellFactory()
	{
		treeQuestView.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>()
		{
			@Override
			public TreeCell<String> call(TreeView<String> treeView)
			{
				FieldEditableTreeCell treeCell = new FieldEditableTreeCell();
				treeCell.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event)
					{
						if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2)
							treeCell.startEdit();
					}
				});

				return treeCell;
			}
		});
	}

	@FXML
	private void onNestElementButton()
	{
		TreeItem<String> selectedItem = treeQuestView.getSelectionModel().getSelectedItem();
		selectedItem.getChildren().add(new TreeItem<String>("Quest sub task"));
	}

}
