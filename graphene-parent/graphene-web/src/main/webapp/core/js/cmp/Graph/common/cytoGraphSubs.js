/**
 * The new Cytoscape graph wrapper class.
 * API for interfacing with cytoscape.js, graph layouts, and state management
 * 
 * Author: Andrew Weller
 * Date: 23/10/2014
 **/
function CytoGraphVis(inId) {

	this.id = inId;
	this.gv = null;
	this.initialized = false;
	this.currentLayout = null;
	this.owner = null;
	this.searchName = "";
	this.dispWidth = 1000;
	this.dispHeight = 800;
	
	this.CONSTANTS = function(key) {
		// protected and immutable constants
		var _legend = {
			textColor: "black",
			textOutlineColor: "white",
			fontSize: 10,
			selectedFontSize: 12,
			nodeSize: 16,
			selectedNodeSize: 24,
			selectedTextColor: "yellow",
			lineColor: "black",
			defaultNode:  '#00FFFF',
			selectedNode: 'DarkBlue',
			//searchedNode: '#FF0000',
			defaultEdge:  '#23A4FF',
			selectedEdge: '#0B0B0B',
			expandedDefNode: '#F66CFB',
			dijkstraPath: "red",
			dijkstraSize: 5,
			outgoingHoverTextColor: "#2D55BE",
			outgoingHoverColor: "#111111",
			outgoingHoverSize: 2,
			incomingHoverTextColor: "#FF8040",
			incomingHoverColor: "#111111",
			incomingHoverSize: 2,
			//expandedDefEdge: '#BE26C4',

			borderWidth: 3,
			borderColor: "black",
			
			fillColor: "rgba(0, 0, 200, 0.75)",
			activeFillColor: "rgba(92, 194, 237, 0.75)",
			
			defaultLayout: "breadthfirst",
			minLeafDistance: 60
		};
		return _legend[key];
	};
	
	var _layoutManager = new LayoutManager(this);
	var _stateManager = new StateManager(this);
	var _dijkstraManager = new DijkstraManager(this);
	var _generator = new GraphGenerator(this);
	
	this.getGv = function() { return this.gv; };
	this.getOwner = function() { return this.owner; };
	this.getCurrentLayout = function() { return this.currentLayout; };
	this.getSearchName = function() { return this.searchName; };
	
	this.getLayoutManager = function() { return _layoutManager; };
	this.getStateManager = function() { return _stateManager; };
	this.getDijkstraManager = function() { return _dijkstraManager; };
	this.getGenerator = function() {return _generator; };
	
	// extend this' API with the layout manager's methods
	this.changeLayout = _layoutManager.changeLayout;
	this.registerLayout = _layoutManager.registerLayout;
	
	// extend this' API with the state manager's methods
	this.deleteNodes = _stateManager.deleteNodes;
	this.deleteEdges = _stateManager.deleteEdges;
	this.exportGraph = _stateManager.exportGraph;
	this.importGraph = _stateManager.importGraph;
	this.hideNode = _stateManager.hideNode;
	this.showNode = _stateManager.showNode;
	this.showEdge = _stateManager.showEdge;
	this.hideEdge = _stateManager.hideEdge;
};

/*
 *	Initialize this graph wrapper div with a cytoscape graph.
 *		config:Object - configuration parameters
 *		owner:Object - Reference to the parent display that houses this graph visualization (useful for scope)
 *		callbackFn:Function - (Optional) a callback function executed after cytoscape initializes the graph
 *		
 *		note: anything else passed to this function will be given to callbackFn as parameters
 */
CytoGraphVis.prototype.initGraph = function( /*config, owner[, callbackFn, ...args]*/ ) {
	var _this = this;
	
	var args = [].slice.apply(arguments);
	var config = args.shift();
	var owner = args.shift();
	var onLoadCallback = args.shift();
	var isEntityGraph = args.shift();
	// at this point, args is an array of whatever other arguments were passed to this function
	
	if (typeof config.width !== "undefined") _this.dispWidth = config.width;
	if (typeof config.height !== "undefined") _this.dispHeight = config.height;
	if (typeof config.rightBorder !== "undefined") _this.dispRightBorder = config.rightBorder;
	if (typeof config.leftBorder !== "undefined") _this.dispLeftBorder = config.leftBorder;
	if (typeof config.topBorder !== "undefined") _this.dispTopBorder = config.topBorder;
	if (typeof config.botBorder !== "undefined") _this.dispBotBorder = config.botBorder;
	
	_this.owner = owner;
	_this.expandedNodes = [];
	_this.onLoadCallback = onLoadCallback;
	_this.args = args;
	
	if (_this.gv !== null) return;
	
	cytoscape({
		container: document.getElementById(_this.id),
		ready: function() {
			overrideRegisterInstance();
			overrideCOSE();
			overrideARBOR();
			
			var style = cytoscape.stylesheet();
			style.selector("node").css({
				'content': 'data(label)',
				'text-valign': 'bottom',
				'color': _this.CONSTANTS("textColor"),
				'background-color': 'data(color)',
				'font-size': _this.CONSTANTS("fontSize"),
				'text-outline-width': 1,
				'text-outline-color': _this.CONSTANTS("textOutlineColor"),
				'width': 'data(size)',
				'height': 'data(size)'
			});
			style.selector("$node > node").css({
				'padding-top': '10px',
				'padding-left': '10px',
				'padding-bottom': '10px',
				'padding-right': '10px',
				'text-valign': 'top',
				'text-halign': 'center',
				'color': _this.CONSTANTS("textColor"),
				'background-color': 'data(color)',
				'font-size': _this.CONSTANTS("fontSize"),
				'text-outline-width': 1,
				'text-outline-color': _this.CONSTANTS("textOutlineColor")   
			});
			style.selector("node:selected").css({
				'background-color': _this.CONSTANTS("selectedNode"),
				'line-color': 'black',
				'text-outline-color': _this.CONSTANTS("selectedTextColor"),
				'target-arrow-color': _this.CONSTANTS("selectedEdge"),
				'source-arrow-color': _this.CONSTANTS("selectedEdge"),
				'border-color': _this.CONSTANTS("lineColor"),
				'font-size': _this.CONSTANTS("selectedFontSize"),
				'width': 'data(size)' + 5,//_this.CONSTANTS("selectedNodeSize"),  
				'height': 'data(size)' + 5//_this.CONSTANTS("selectedNodeSize")
			});
			// disable until Cytoscape 2.3.7 works.
			// haystack edges look weird in 2.2.12
			if (/*isEntityGraph*/ false) {
				style.selector("edge").css({
					'curve-style': 'haystack',	// !
					'haystack-radius': 0,		// !
					'content':'data(label)',   
					'text-valign': 'center',
					'color': _this.CONSTANTS("textColor"),
					'text-outline-width': 1,
					'line-style': 'data(lineStyle)',
					'font-size': _this.CONSTANTS("fontSize"),
					'text-outline-color': _this.CONSTANTS("textOutlineColor"),
					'line-color': 'data(color)',
					'width': 'data(count)'
				});
			} else {
				style.selector("edge").css({
					'content':'data(label)',   
					'text-valign': 'center',
					'color': _this.CONSTANTS("textColor"),
					'text-outline-width': 1,
					'line-style': 'data(lineStyle)',
					'font-size': _this.CONSTANTS("fontSize"),
					'text-outline-color': _this.CONSTANTS("textOutlineColor"),
					'line-color': 'data(color)',
					'target-arrow-color': 'data(color)',
					'target-arrow-shape': 'triangle',
					'width': 'data(count)'
				});
			}
			style.selector("edge:selected").css({
				'background-color': _this.CONSTANTS("selectedEdge"),
				'line-color': _this.CONSTANTS("lineColor"),
				'text-outline-color': _this.CONSTANTS("selectedTextColor"),
				'target-arrow-color': _this.CONSTANTS("lineColor"),
				'source-arrow-color': _this.CONSTANTS("lineColor"),
				'border-color': _this.CONSTANTS("lineColor"),
				'font-size': _this.CONSTANTS("fontSize")
			});
			style.selector(".on-path").css({
				'line-color': _this.CONSTANTS("dijkstraPath"),
				'target-arrow-color': _this.CONSTANTS("dijkstraPath"),
				'width': _this.CONSTANTS("dijkstraSize")
			});
			style.selector(".parent-of").css({
				'color': _this.CONSTANTS("outgoingHoverTextColor"),
				'line-color': _this.CONSTANTS("outgoingHoverColor"),
				'target-arrow-color': _this.CONSTANTS("outgoingHoverColor"),
				'text-outline-color': _this.CONSTANTS("outgoingHoverColor")
			});
			style.selector(".child-of").css({
				'color': _this.CONSTANTS("incomingHoverTextColor"),
				'line-color': _this.CONSTANTS("incomingHoverColor"),
				'target-arrow-color': _this.CONSTANTS("incomingHoverColor"),
				'text-outline-color': _this.CONSTANTS("incomingHoverColor")
			});
			style.selector("node.super-node").css({
				'border-width': _this.CONSTANTS("borderWidth"),
				'border-style': 'solid', // currently does not modify anything
				'border-color': _this.CONSTANTS("borderColor")
			});
			
			$("#" + _this.id).cytoscape({
				zoomingEnabled: true,
				userZoomingEnabled: true,
				panningEnabled: true,
				userPanningEnabled: true,
				boxSelectionEnabled: true,
				touchTapThreshold: 8,
				desktopTapThreshold: 4,
				autolock: false,
				autoungrabify: false,
				autounselectify: false,
				showOverlay: false,
				motionBlur: false,
				layout: _this.getLayoutManager().getRegisteredLayouts(_this.CONSTANTS("defaultLayout"))[0].config,
				style: style
			});
			
			// declare a reference to the completed cytoscape graph object as this.gv
			_this.gv = $("#" + _this.id).cytoscape("get");
			
			// if the plug-in is not included for any reason, do not try to initialize it
			try {
				if (typeof _this.gv.cxtmenu !== "function")
					throw "You must include 'cytoscape.js-cxtmenu.js' in the .html file";
				
				_this.gv.cxtmenu({
					menuRadius: 75, selector: 'node', activePadding: 0,
					fillColor: _this.CONSTANTS("fillColor"), 
					activeFillColor: _this.CONSTANTS("activeFillColor"), 
					commands: [{
						content: "Unmerge",
						select: function() {
							var node = this;
							if (_this.owner.givePromptToUnmerge) { _this.owner.givePromptToUnmerge([node]); } 
							else if (_this.owner.unmergeNode) { _this.owner.unmergeNode(node); } 
							else { console.log("Capability to unmerge nodes is undefined"); }
						}
					}, {
						content: "Connect Node",
						select: function() {
							var node = this;
							var gg = _this.getGenerator();
							if (typeof gg !== "undefined") {
								gg.clear();
								gg.setParent(node);
								gg.changeState("CONNECT");
								gg.givePrompt();
							}
						}
					}, {
						content: "Add Node",
						select: function() {
							var node = this;
							var gg = _this.getGenerator();
							if (typeof gg !== "undefined") {
								gg.clear();
								gg.setParent(node);
								gg.changeState("ADD");
								gg.givePrompt();
							}
						}
					}, {
						content: "Merge Selected Nodes",
						select: function() {
							var node = this;
							var selectedNodes = _this.gv.$("node:selected");
							if (_this.owner.givePromptToMerge) { _this.owner.givePromptToMerge(node, selectedNodes); } 
							else if (_this.owner.mergeNodes) { _this.owner.mergeNodes(node, selectedNodes); } 
							else { console.log("Capability to merge nodes is undefined"); }
						}
					}, {
						content: "Expand",
						select: function() {
							var node = this;
							if (_this.owner.expand) { _this.owner.expand(node); } 
							else { console.log("expand() is undefined"); }
						}
					}, {
						content: "Shortest Path",
						select: function() {
							var node = this;
							var dm = _this.getDijkstraManager();
							if (typeof dm !== "undefined") {
								dm.clear();
								dm.setRoot(node);
								dm.setWait(true);
								console.log("Set node id='" + node.data("id") + "' as root.");
							} else { console.log("dijkstra pathfinding is unavailable."); }
						}
					}, {
						content: "Edit",
						select: function() {
							var node = this;
							if (_this.owner.editElement) { _this.owner.editElement(node); } 
							else { console.log("editElement() is undefined"); }
						}
					}, {
						content: "Unexpand",
						select: function() {
							var node = this;
							if (_this.owner.unexpand) { _this.owner.unexpand(node); }
							else { console.log("unexpand() is undefined"); }
						}
					}]
				}); // END NODE CONTEXT MENU
				
				_this.gv.cxtmenu({
					menuRadius: 75, selector: 'edge', activePadding: 0,
					fillColor: _this.CONSTANTS("fillColor"), 
					activeFillColor: _this.CONSTANTS("activeFillColor"), 
					commands: [{
						content: "Delete Edge",
						select: function() {
							var edge = this;
							_this.deleteEdges([edge]);
						}
					}, {
						content: "Edit Edge",
						select: function() {
							var edge = this;
							if (_this.owner.editElement) { _this.owner.editElement(edge); } 
							else { console.log("editElement() is undefined"); }
						}
					}, {
						content: "Hide Edge",
						select: function() {
							var edge = this;
							_this.hideEdge(edge, false);
						}
					}]
				}); // END EDGE CONTEXT MENU
			} catch(e) {
				console.log(e);
				console.log("Context Menu on cytoscape graph " + _this.id + " will be unavailable.");
			}
			
			// if a callback function was provided, pass it any remaining parameters and call it
			if (_this.onLoadCallback && typeof _this.onLoadCallback == "function") {
				_this.onLoadCallback.apply(_this.owner, _this.args);
			}
			
			_this.initialized = true;
			_this.setHandlers();
		}
	});
};

/*
 *	Define the event handlers for user-interaction with this graph.
 */
CytoGraphVis.prototype.setHandlers = function() {
	var _this = this;

	var _highlightElements = function(ele, dir, addCls) {
		var accessProp = (dir == "incoming") ? "source" : "target";
		var cssClass = (dir == "incoming") ? "child-of" : "parent-of";
		
		var node = _this.gv.nodes("node[id = '" + ele.data(accessProp) + "']")[0];
		if (!ele.selected()) {
			(addCls == true) ? ele.addClass(cssClass) : ele.removeClass(cssClass);
		}
		if (!node.selected()) {
			(addCls == true) ?  node.addClass(cssClass) : node.removeClass(cssClass);
		}
	};
	
	this.gv.on("click", function(e) {
		var gg = _this.getGenerator();
		
		if (e.cyTarget === _this.gv) {
			var pos = e.cyPosition;
			// if you're trying to add a node and click white-space, add a node
			if (gg.stateMatches("ADD")) {
				gg.addNode(pos.x, pos.y);
				gg.clear();
				gg.givePrompt();
			}
			// if you're trying to connect a node but click white-space, reset the manager
			// to prevent unwanted interaction later on
			if (gg.stateMatches("CONNECT")) {
				gg.clear();
				gg.givePrompt();
			}
		}
	});
	
	this.gv.on("click", "node", function(e) {
		var dm = _this.getDijkstraManager();
		var gg = _this.getGenerator();
		if (dm.isWaiting === true) {
			dm.setWait(false);
			dm.setDest(e.cyTarget);
			var results = dm.run(false);
			
			results.paths.edges().addClass("on-path");
			
			if (_this.owner.getProgressBar) {
				var pb = _this.owner.getProgressBar();
				if (pb) pb.updateProgress(1, "Shortest Path calculated; Number of hops is " + results.distance + ".");
			}
		} else if (gg.stateMatches("CONNECT")) {
			gg.connectNodes(e.cyTarget);
			gg.clear();
			gg.givePrompt();
		} else if (gg.stateMatches("ADD")) {
			// if you're trying to add a new node but click an existing one, reset the manager
			// to prevent unwanted interaction later on
			gg.clear();
			gg.givePrompt();
		} else {
			var node = e.cyTarget;
			// if ctrl + shift, select all nodes of the clicked node's type
			if (e.originalEvent.ctrlKey == true && e.originalEvent.shiftKey == true) {
				e.cy.elements().unselect();
				e.cy.nodes("[idType = '" + node.data("idType") + "']").select();
				e.cy.nodes("[id = '" + node.data("id") + "']").unselect();
			} 
			// if alt + shift, select all neighbors of the clicked node
			else if (e.originalEvent.altKey == true && e.originalEvent.shiftKey == true) {
				e.cy.elements().unselect();
				node.connectedEdges().connectedNodes().select();
				e.cy.nodes("[id = '" + node.data("id") + "']").unselect();
			} 
			// if shift key is not held down, deselect everything else before selecting the clicked node
			else if (e.originalEvent.shiftKey !== true) {
				e.cy.elements().unselect();
				e.cy.nodes("[id = '" + node.data("id") + "']").unselect();
			}
			//node.select();
		}
	});
	
	this.gv.on("select", "node", function(e) {
		var node = e.cyTarget;
		
		// cytoscape is funky in that it will still select hidden elements
		if (node.hidden()) {
			node.unselect();
			return;
		}
		
		if (typeof _this.owner.nodeClick !== "undefined") {
			_this.owner.nodeClick(node);
		}
	});
	
	this.gv.on("select", "edge", function(e) {
		var edge = e.cyTarget;
		
		// cytoscape is funky in that it will still select hidden elements
		if (edge.hidden()) {
			edge.unselect();
			return;
		}
		
		if (typeof _this.owner.edgeClick !== "undefined") {
			_this.owner.edgeClick(edge);
		}
	});
	
	this.gv.on("cxttapend", "edge", function(e) {
		var edge = e.cyTarget;
		if (typeof _this.owner.edgeRightClick !== "undefined") {
			_this.owner.edgeRightClick(edge);
		} 
	});
	
	this.gv.on("mouseover", "node", function(e) {
		var node = e.cyTarget;
		
		var outgoingEdges = _this.gv.edges("edge[source = '" + node.data("id") + "']");
		var incomingEdges = _this.gv.edges("edge[target = '" + node.data("id") + "']");
		
		outgoingEdges.each(function(i, ele) {
			_highlightElements(ele, "outgoing", true);
		});
		
		incomingEdges.each(function(i, ele) {
			_highlightElements(ele, "incoming", true);
		});
	});
	
	this.gv.on("mouseout", "node", function(e) {
		var node = e.cyTarget;
		
		var outgoingEdges = _this.gv.edges("edge[source = '" + node.data("id") + "']");
		var incomingEdges = _this.gv.edges("edge[target = '" + node.data("id") + "']");
		
		outgoingEdges.each(function(i, ele) {
			_highlightElements(ele, "outgoing", false);
		});
		
		incomingEdges.each(function(i, ele) {
			_highlightElements(ele, "incoming", false);
		});
	});
};

CytoGraphVis.prototype.reset = function() {
	this.setHandlers();
	this.changeLayout(this.CONSTANTS("defaultLayout"), {});
};

/*
 *	Show all elements on this graph and toggle their class appropriately
 */
CytoGraphVis.prototype.showAll = function(isFilter) {
	var scope = this;
	var selector = "";
	if (typeof isFilter !== "undefined") {
		selector = (isFilter == true) ? ".toggled-filter" : ".toggled-hide";
	}
	
	// gather all elements, nodes and edges, that may have been hidden via Hide or Filter 
	scope.gv.elements(selector).each(function(i, ele) {
		if (ele.isNode()) {
			scope.showNode(ele, isFilter);
		} else if (ele.isEdge()) {
			scope.showEdge(ele, isFilter);
		}
	});
};

/*
 *	Load the following json into cytoscape for processing.
 *		json:Object - json representation of the graph
 *		name:String - identifier of the root node/entity 
 */
CytoGraphVis.prototype.showGraph = function(json, name, useSaved) {
	var scope = this;
	scope.searchName = name;
	scope.gv.load(json); // <---- FIXME: renders elements even when ele.data("visible") == false
	if (useSaved === true) {
		scope.changeLayout("preset", {});
	} else { 
		scope.changeLayout(scope.CONSTANTS("defaultLayout"), {});
	}
	
	try {
		if (useSaved === true) {
			var btn = scope.getOwner().getToolbar().getEnabledLayoutBtn();
			if (btn) btn.toggle(false);
		} else {
			var btn = scope.getOwner().getToolbar().getHierarchyBtn();
			if (btn) btn.toggle(true); 
		}
	} catch (e) {
		console.error(e.message);
	}
	
	// for some reason, elements marked as hidden to not remain hidden when loaded into cytoscape.
	// investigate that later.  for now, just re-hide the hidden elements
	scope.gv.elements("[!visible]").each(function(i, ele) {
		var isFilter = ele.hasClass("toggled-filter");
		if (ele.isNode()) {scope.hideNode(ele, isFilter);}
		else if (ele.isEdge()) {scope.hideEdge(ele, isFilter);}
	});
};

CytoGraphVis.prototype.resize = function(w, h) {
	if (this.initialized && typeof this.gv != "undefined") {
		this.gv.fit();
	}
	this.dispWidth = w;
	this.dispHeight = h;
};

CytoGraphVis.prototype.clear = function() {
	this.gv.elements().remove();
};

/*
 * 	Given a sub-graph and a central node, place the expanded nodes in an orbit
 *	around the "Origin Node".  Note: this method does not determine what the expanded,
 *	or 1-hop, graph will be.
 *		json:JsonObject - JSON graph representation of the nodes/edges found in 1-hop
 *		innode:Cytoscape Node - "Origin Node" to extend from the graph and show its 1-hop neighbors 
 */
CytoGraphVis.prototype.showGraph1Hop = function(json, innode) {
	var pos = innode.position();
	var removedNodeIDs = [];
	var superNodes = this.gv.$("node.super-node");
	
	// breadthfirst recursion
	var _recurse = function(superNode, id) {
		// BASE CASE: if this node matches id, return false
		var thisId = (typeof superNode.data == "function") ? superNode.data("id") : superNode.data.id;
		if (thisId == id) { return false; }
		
		// BASE CASE: if there are no subnodes in this node, return false
		var subNodes = (typeof superNode.data == "function") ? superNode.data("subNodes") : superNode.data.subNodes;
		if (subNodes == null || typeof subNodes == "undefined" || subNodes.length == 0) { return false; }
		
		// BASE CASE: check all subnodes of this node
		for (var i = 0; i < subNodes.length; i++) {
			if (subNodes[i].data.id == id) { return true; }
		}
		
		// RECURSIVE CASE: none of the subnodes at this level match; check one level down for each subnode
		for (i = 0; i < subNodes.length; i++) {
			if ( _recurse(subNodes[i], id) ) { return true; }
		}
		
		// we checked, re-checked, and double-checked;  the id just isn't here
		return false;
	};
	
	// randomly select an edge attached to this node and use it to push it away from the rest of the graph
	/*
	try {
		var connectedNodes = innode.connectedEdges().connectedNodes();
		var neighbor = null;
		connectedNodes.each(function(i, n) {
			if (n.data().id != innode.data().id) {
				neighbor = n;
				return;
			}
		});
		var n_pos = neighbor.position();
		var dx = pos.x - n_pos.x;
		var dy = pos.y - n_pos.y;
		var newX = pos.x + dx * 1.5;
		var newY = pos.y + dy * 1.5;
		innode.position({x: newX, y: newY});
		pos = innode.position();
	} catch(e) {
		console.log(e.message);
	}
	*/
	var nodes = json.nodes;
	for (var i = 0; i < nodes.length; i++) {
		var node = nodes[i];
		var l = nodes.length;
		var rad = 2 * Math.PI * i / l;
		var radius = this.CONSTANTS("minLeafDistance") + l + l;
		
		// check all supernodes and make sure this node json does not match a subnode
		superNodes.each(function(index, n) {
			if ( _recurse(n, node.data.id) ) {
				console.log("while searching recursively, found duplicate node name='" + node.data.name + "'");
				removedNodeIDs.push(node.data.id);
				nodes.splice(i--, 1);
			}
		});
		
		if (node.data.id != innode.data("id")) {
			// node.data.color = this.CONSTANTS("expandedDefNode");
			node.data.expanded = true;
			node.data.parentId = innode.data("id");
			node.position = {
				x: pos.x + (radius * Math.cos(rad)),
				y: pos.y + (radius * Math.sin(rad))
			};
		}
	}
	
	// prevent any duplicate edges from being added to the graph
	var edges = json.edges;
	for (i = 0; i < edges.length; i++) {
		var edge = edges[i];
		
		var a = edge.data.amount;
		var l = edge.data.label;
		var s = edge.data.source;
		var t = edge.data.target;
		
		edge.data.expanded = true;
		
		// if this edge was attached to a node that was removed, splice it out of the json and continue to the next edge
		if (removedNodeIDs.indexOf(t) != -1 || removedNodeIDs.indexOf(s) != -1) {
			edges.splice(i--, 1);
			continue;
		}
		
		var amountCondition = (typeof a == "string" && a.length > 0) ? "[amount = '"+a+"']" : "";
		var labelCondition = (typeof l == "string" && l.lenght > 0) ? "[label = '"+l+"']" : "";
		
		// can't use IDs since they are not present in the JSON.  Only available after this.gv.add(json), which is too late.
		// (this.source == that.source && this.target == that.target && this.amount == that.amount && this.label == that.label) is 
		// the best match condition so far.
		var matchedEdges = this.gv.$("edge[source = '"+s+"'][target = '"+t+"']" + amountCondition + labelCondition);
		
		// if edge already exists on the graph, splice it out of the json before it's loaded
		if (matchedEdges.length > 0) {
			edges.splice(i--, 1);
			
			matchedEdges.each(function(index, e){
				console.log("Pruned edge with id='" + e.data("id") + "'");
			});
		}
	}
	
	this.gv.add(json);
	
	var retJson = {};
	retJson.nodes = this.gv.nodes().jsons();
	retJson.edges = this.gv.edges().jsons();
	return retJson;
};

/*
 * 	Delete all leaf nodes from the given node, essentially undoing a 1-hop expansion.
 *  Note: any edges that were created to existing nodes will remain
 *  	innode:Cytoscape Node - "Origin Node" from which to retract all leaf nodes
 */
CytoGraphVis.prototype.unexpand1Hop = function(innode) {
	var _this = this;
	var nodesToDelete = [];
	var edgesToDelete = [];
	var nonLeafNodeIDs = [];
	
	// TODO: make a public function somewhere
	var _isLeaf = function(node) {
		var possibleNeighbors = node.connectedEdges().connectedNodes();
		var confirmedNeighbors = [];
		possibleNeighbors.each(function(index, n) {
			if (n.data("id") != node.data("id")) {
				confirmedNeighbors.push(n.data("id"));
			}
		});
		return confirmedNeighbors.length == 1;
	};
	
	// "[?expanded]" filters for all elements which element.data("expanded") == true
	innode.connectedEdges("[?expanded]").connectedNodes().each(function(i, n) {
		if ( _isLeaf(n) ) { 
			if (n.data("id") !== innode.data("id")) {
				nodesToDelete.push(n);
			}
		} else {
			// TODO: handle non-leaf expanded nodes differently, if at all
			nonLeafNodeIDs.push(n.data("id"));
		}
	});
	
	innode.connectedEdges("[?expanded]").each(function(i, e) {
		var s = e.data("source");
		var t = e.data("target");
		if (s !== t /*&& nonLeafNodeIDs.indexOf(t) == -1 && nonLeafNodeIDs.indexOf(s) == -1*/) {
			edgesToDelete.push(e);
		}
	});
	
	// if nothing can be deleted, notify the user.  else delete available nodes/edges
	if (nodesToDelete.length <= 0 && edgesToDelete.length <= 0) {
		var errorMsg = "This node has no leaves (that can be deleted).";
		if (_this.getOwner().getProgressBar) {
			var pb = this.getOwner().getProgressBar();
			if (pb) pb.updateProgress(1, errorMsg);
			else alert(errorMsg);
		}
	} else {
		_this.deleteNodes(nodesToDelete, true);
		_this.deleteEdges(edgesToDelete, true);
		
		if (innode.connectedEdges().length <= 0) {
			_this.deleteNodes([innode], true);
		}
	}
};

// LayoutManager constructor
function LayoutManager(graphRef) {
	var _this = this;
	
	var _registeredLayouts = {};
	
	/*
	 *	Returns an array of the currently registered layouts whose names match any of the
	 *	key strings passed as this function's array parameter.
	 *		keysArr:Array - (optional) array containing layout identifier strings.  Only layouts
	 *						whose keys match any of these identifiers will be returned.
	 *		returns Array - array of objects, where each object has a layoutName property and
	 *						config options for that layout.
	 */
	this.getRegisteredLayouts = function(keysArr) {
		var arr = [];
		var returnAll = true;
		if (typeof keysArr == "undefined") { keysArr = []; }
		if (typeof keysArr == "string") { keysArr = [keysArr]; }
		if (typeof keysArr.length !== "undefined") { returnAll = keysArr.length <= 0; }
		
		for (var key in _registeredLayouts) {
			if (!_registeredLayouts.hasOwnProperty(key)) continue;
			if (!returnAll && keysArr.indexOf(key) == -1) continue;
			
			var layout = _registeredLayouts[key];
			var options = {};
			
			for (var prop in layout) {
				if (!layout.hasOwnProperty(prop)) continue;
				var val = layout[prop];
				//if (typeof val !== "function") {
					options[prop] = val;
				//}
			}
			
			arr.push({
				layoutName: key,
				config: options
			});
		}
		
		return arr;
	};
	
	/*
	 * private function to merge the properties of the two parameters, with optsToMerge having overwrite priority.
	 *		baseOpts:Object - default configuration object
	 *		optsToMerge:Object - overrides for the default configuration
	 * 		returns mergedOptions:Object - combination of baseOpts + optsToMerge
	 */
	var _mergeOptions = function(baseOpts, optsToMerge) {
		var mergedOptions = {};
		
		for (var key in baseOpts) {
			if (baseOpts.hasOwnProperty(key)) {
				var value = baseOpts[key];
				mergedOptions[key] = value;
			}
		}
		
		for (var key in optsToMerge) {
			if (optsToMerge.hasOwnProperty(key)) {
				var value = optsToMerge[key];
				mergedOptions[key] = value;
			}
		}
		return mergedOptions;
	};
	
	/*
	 *	Changes the current graph layout if layoutName matches a registered layout.
	 *		layoutName:String - key to access the registered layouts
	 *		config:Object - (optional) overrides for the layout's configuration
 	 */
	this.changeLayout = function(layoutName, config) {
		if (graphRef.initialized == false) return;
		
		if (_registeredLayouts.hasOwnProperty(layoutName)) {
			var options = _registeredLayouts[layoutName];
			options = _mergeOptions(options, config);
			graphRef.currentLayout = options.name;
			
			if (typeof options.beforeLayout == "function") {
				options.beforeLayout();
			}
			
			graphRef.gv.layout(options);
		}
	};
	
	/*
	 *	Add a new layout option to the LayoutManager.
	 *		layoutName:String - key to access the registered layouts
	 *		config:Object - configuration parameters for this new layout
	 *		stopFn:Function - (optional) callback on layoutstop event
	 *		progressFn:Function - (optional) callback for layout's procedural status, if applicable
	 *		startFn:Function - (optional) callback on layoutready event
	 */
	this.registerLayout = function(layoutName, scope, config, stopFn, progressFn, startFn) {
	
		if (typeof layoutName == "undefined" || typeof config == "undefined") return;
		
		_registeredLayouts[layoutName] = config;
		
		if (typeof stopFn == "function") {
			// if stopFn was provided, assign it to this layout options using the function's name as a key
			_registeredLayouts[layoutName][stopFn.name] = function() { stopFn.apply(scope, arguments); };
		}
		if (typeof progressFn == "function") {
			// if progressFn was provided, assign it to this layout options using the function's name as a key
			_registeredLayouts[layoutName][progressFn.name] = function() { progressFn.apply(scope, arguments); };
		}
		if (typeof startFn == "function") {
			// if startFn was provided, assign it to this layout options using "beforeLayout" as a key (so it can be referenced with a default)
			_registeredLayouts[layoutName]["beforeLayout"] = function() { startFn.apply(scope, arguments); };
		}
	};
	
	/*
	 *	Deletes the registered layout whose key matches layoutName.
	 *		layoutName:String - identifier key for the registered layout to be deleted.
	 */
	this.unregisterLayout = function(layoutName) {
		if (typeof layoutName == "undefined" || typeof layoutName !== "string") return;
		if (_registeredLayouts.hasOwnProperty(layoutName)) {
			delete _registeredLayouts[layoutName];
		}
	};
	
	/*
	 *	For each Origin Node passed to this method, its leaf nodes will be repositioned to orbit around
	 *	the Origin Node.  Neighbors to the Origin Node who have neighbors of their own will be untouched.
	 *		nodelist:Object - Contains one or more "origin nodes" and an array of each one's leaves
	 *			nodelist resembles {
	 *				"origin_id_1" : {
	 *					node: Cytoscape Node Object of the origin node,
	 *					leaves: [ Cytoscape Node Objects of the leaves ]
	 *				},
	 *				...
	 *			}
	 */
	this.repositionClusters = function(nodelist) {
        var minDist = graphRef.CONSTANTS("minLeafDistance");
		for (var key in nodelist) {
			var coreNode = nodelist[key].node;
			var leaves = nodelist[key].leaves;
			
			var corePos = coreNode.position();
			var l = leaves.length;
			var rad = 0;
			var r = minDist + l + l;
			
			for (var i = 0; i < l; i++) {
				var leaf = leaves[i];
				rad = 2 * Math.PI * i / l;
				var newX = corePos.x + (r * Math.cos(rad));
				var newY = corePos.y + (r * Math.sin(rad));
				
				leaf.position({x: newX, y: newY});
			}
		}
    };
	
	// ========================================= REGISTER DEFAULT LAYOUTS ========================================= /
	
	this.registerLayout("preset", graphRef, {
			name: "preset", fit: true, animate: false
		},
		function stop() {
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				// if (pb) pb.updateProgress(1, "100%");
			}
		}
	);
	
	this.registerLayout("grid", graphRef, { 
			name: "grid", fit: true, rows: undefined, column: undefined 
		},
		function stop() {
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				//if (pb) pb.updateProgress(1, "100%");
			}
		}
	); // END GRID
	
	this.registerLayout("circle", graphRef, {
			name: "circle", fit: true, rStepSize: 10, padding: 30, counterClockwise: false
		},
		function stop() {
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				//if (pb) pb.updateProgress(1, "100%");
			}
		}
	); // END CIRCLE
	
	this.registerLayout("breadthfirst", graphRef, {
			name: "breadthfirst", fit: true, directed: true, padding: 15, circle: false, roots: undefined,
			//maximalAdjustments: 20
		},
		function stop() {
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				//if (pb) pb.updateProgress(1, "100%");
			}
		}
	); // END BREADTHFIRST
	
	this.registerLayout("cose", graphRef, {
			name: "cose", refresh: 10, fit: true, randomize: true, debug: false, nodeRepulsion: 99999999,
			nodeOverlap: 5000, idealEdgeLength: 10, edgeElasticity: 1, nestingFactor: 5, gravity: 250,
			numIter: 5000, initialTemp: 500, coolingFactor: 0.95, minTemp: 8,
			feedbackRate: 5, intervalRate: 1
		},
		function stop() {
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(true);
				stopBtn.setCurrentLayout(undefined);
			}

			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(1, "100%");
			}
		},
		function onFeedback(opt) {
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) {
					var progress = _this.minTemp / opt.temp;
					pb.updateProgress(progress, Math.floor(progress * 100) + "%");
				}
			}
		},
		function ready() {
			_this.minTemp = _registeredLayouts["cose"].minTemp;
			
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(false);
				stopBtn.setCurrentLayout("COSE");
			}
		}
	); // END COSE
	
	this.registerLayout("arbor-snow", graphRef, {
			name: "arbor", liveUpdate: true, maxSimulationTime: 90000, fit: true, padding: [50, 10, 50, 10],
			ungrabifyWhileSimulating: true, repulsion: 8000, stiffness: 300, friction: undefined,
			gravity: true, fps: 240, precision: 0.2, nodeMass: undefined, edgeLength: 3, stepSize: 1
		},
		function stop() {
			_this.progress = 0;
		
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(true);
				stopBtn.setCurrentLayout(undefined);
			}
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(1, "100%");
			}
		},
		function stableEnergy(en) {
			var max_progress = 0.3 / en.max;
			var mean_progress = 0.2 / en.mean;
			var avg_progress = (max_progress + mean_progress) / 2;
			
			if (avg_progress < 1 && avg_progress > _this.progress) {
				_this.progress = avg_progress;
			}
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(_this.progress, Math.floor(_this.progress * 100) + "%");
			}
			return (en.max <= 0.3) || (en.mean <= 0.2);
		},
		function ready() {
			_this.progress = 0;
			
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(false);
				stopBtn.setCurrentLayout("ARBOR");
			}
		}
	); // END ARBOR-SNOW
	
	this.registerLayout("arbor-wheel", graphRef, {
			name: "arbor", liveUpdate: true, maxSimulationTime: 90000, fit: true, padding: [50, 10, 50, 10],
			ungrabifyWhileSimulating: true, repulsion: 8000, stiffness: 300, friction: undefined,
			gravity: true, fps: 240, precision: 0.2, nodeMass: undefined, edgeLength: 3, stepSize: 1
		},
		function stop() {
			_this.progress = 0;
		
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(true);
				stopBtn.setCurrentLayout(undefined);
			}
			
			var anodes = graphRef.gv.nodes();
			var originNodes = {};

			// TODO: make a public function somewhere
			var _isLeaf = function(node) {
				var possibleNeighbors = node.connectedEdges().connectedNodes();
				var confirmedNeighbors = [];
				possibleNeighbors.each(function(index, n) {
					if (n.data().id != node.data().id) {
						confirmedNeighbors.push(n.data().id);
					}
				});
				return confirmedNeighbors.length == 1;
			};
			
			// iterate over all nodes.  if a leaf is found, designate its single neighbor to be
			// an "origin node".  Origin Nodes have an array of all their leaves. 
			anodes.each(function(indx, node) {
				if (_isLeaf(node)) {
					var neighbors = node.connectedEdges().connectedNodes();
					neighbors.each(function(index, n) {
						var neighborId = n.data().id;
						if (neighborId != node.data().id) {
							//node.data().color = "purple";
							if (originNodes.hasOwnProperty(neighborId)) {
								originNodes[neighborId].leaves.push(node);
							} else {
								originNodes[neighborId] = {
									node: n,
									leaves: [node]
								};
							}
						}
					});
				}
			});

			// For each origin node, reposition its leaves in its orbit
			_this.repositionClusters(originNodes);
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(1, "100%");
			}
		},
		function stableEnergy(en) {
			var max_progress = 0.3 / en.max;
			var mean_progress = 0.2 / en.mean;
			var avg_progress = (max_progress + mean_progress) / 2;
			
			if (avg_progress < 1 && avg_progress > _this.progress) {
				_this.progress = avg_progress;
			}
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(_this.progress, Math.floor(_this.progress * 100) + "%");
			}
			return (en.max <= 0.3) || (en.mean <= 0.2);
		},
		function ready() {
			_this.progress = 0;
			
			if (graphRef.owner.getStopButton) {
				var stopBtn = graphRef.owner.getStopButton();
				stopBtn.setDisabled(false);
				stopBtn.setCurrentLayout("ARBOR");
			}
		}
	); // END ARBOR-WHEEL
}

// StateManager constructor
function StateManager(graphRef) {
	var _this = this;
	
	/*
	 *	Gather the metadata and graph JSON for this cytoscape graph and
	 *	return it.
	 *		returns json:Object
	 */
	this.exportGraph = function() {
		var json = {};
		json.timestamp = new Date().getTime();
		json.searchName = graphRef.getSearchName();
		json.currentLayout = graphRef.getCurrentLayout();
		
		var outNodes = graphRef.gv.nodes().jsons();
		var outEdges = graphRef.gv.edges().jsons();
		
		json.graph = {};
		json.graph.nodes = outNodes;
		json.graph.edges = outEdges;
		
		return json;
	};
	
	/*
	 *	Loads a pre-defined cytoscape graph's JSON object, preserving the elements'
	 *	visibility and positions on the X/Y plane.
	 *		json:Object - graph JSON to load into cytoscape.
	 */
	this.importGraph = function(json) {
		if (json && json.graph && json.graph.nodes && json.graph.nodes.length > 0) {
			graphRef.clear();
			graphRef.searchName = json.searchName;
			graphRef.gv.add(json.graph);
			
			graphRef.gv.nodes().each(function(i, n) {
				if (n.data().visible == false) {
					n.hide();
				}
			});
			
			graphRef.gv.edges().each(function(i, e) {
				if (e.data().visible == false) {
					e.hide();
				}
			});
		} else {
			console.error("The graph json for import is empty or invalid");
		}
	};
	
	/*
	 *	Remove (not hide) nodes from the cytosape graph AND the json representation
	 *	stored by most parent containers of a graph.
	 *		nodes:Array - cytosape nodes to be deleted
	 */
	this.deleteNodes = function(nodes, bypass) {
		var errorMsg = null;
		for (var i = 0; i < nodes.length; i++) {
			var id = nodes[i].data().id;
			
			// if node is not user-generated && bypass is not true, continue;
			if (nodes[i].data("generated") !== true && bypass !== true) {
				errorMsg = "You can only delete user-generated nodes.";
				continue;
			}
			
			// remove all edges whose source == id
			graphRef.gv.remove("edge[source='" + id + "']");
			
			// remove all edges whose target == id
			graphRef.gv.remove("edge[target='" + id + "']");
			
			// remove all nodes whose id == id
			graphRef.gv.remove("node[id='" + id + "']");

			// most graph implementations store a json representation of their cyto graphs;
			// remove traces of nodes and edges involved with selected node IDs
			if (typeof graphRef.owner.json !== "undefined") {
				var j;
				var json = graphRef.owner.json;
				
				for (j = 0; j < json.nodes.length; j++) {
					var jsonNode = graphRef.owner.json.nodes[j];
					if (jsonNode.data.id == id) {
						json.nodes.splice(j, 1);
						break;  // there should only be one node with this id; stop iterating
					}
				}
				
				for (j = 0; j < json.edges.length; j++) {
					var jsonEdge = graphRef.owner.json.edges[j];
					if (jsonEdge.data.source == id || jsonEdge.data.target == id) {
						json.edges.splice(j, 1);
						j--;
					}
				}
			}
		}
		
		if (errorMsg != null && graphRef.getOwner().getProgressBar) {
			var pb = graphRef.getOwner().getProgressBar();
			if (pb) pb.updateProgress(1, errorMsg);
			else alert(errorMsg);
		}
	};
	
	this.deleteEdges = function(edges, bypass) {
		var errorMsg = null;
		for (var i = 0; i < edges.length; i++) {
			var id = edges[i].data("id");
			
			// if edge is not user-generated && bypass is not true, continue;
			if (edges[i].data("generated") !== true && bypass !== true) {
				errorMsg = "You can only delete user-generated edges.";
				continue;
			}
			// remove all edges with matching id
			graphRef.gv.remove("edge[id='" + id + "']");
			
			// most graph implementations store a json representation of their cyto graphs;
			// remove traces of edges involved with selected id
			if (typeof graphRef.owner.json !== "undefined") {
				var json = graphRef.owner.json;
				for (var j = 0; j < json.edges.length; j++) {
					var jsonEdge = graphRef.owner.json.edges[j];
					if (jsonEdge.data.id == id) {
						json.edges.splice(j, 1);
						break; // there should only be one edge with this id;  stop iterating
					}
				}
			}
		}
		
		if (errorMsg != null && graphRef.getOwner().getProgressBar) {
			var pb = graphRef.getOwner().getProgressBar();
			if (pb) pb.updateProgress(1, errorMsg);
			else alert(errorMsg);
		}
	};
	
	this.showNode = function(node, isFilter) {
		if (node.hidden()) {
			node.show();
			node.data().visible = true;
			var edges = node.connectedEdges();
			if (edges) {
				edges.each(function(i, e) {
					_this.showEdge(e, isFilter);
				});
			}
			var cls = isFilter ? "toggled-filter" : "toggled-hide";
			node.removeClass(cls);
		}
	};
	
	this.hideNode = function(node, isFilter) { 
		if (node.visible()) {
			var edges = node.connectedEdges();
			if (edges) {
				edges.each(function(i, e) {
					_this.hideEdge(e, isFilter);
				});
			}
			node.hide();
			node.data().visible = false;
			var cls = isFilter ? "toggled-filter" : "toggled-hide";
			node.addClass(cls);
		}
	};
	
	this.showEdge = function(edge, isFilter) {
		if (edge.hidden()) {
			edge.show();
			edge.data().visible = true;
			var cls = isFilter ? "toggled-filter" : "toggled-hide";
			edge.removeClass(cls);
		}
	};
	
	this.hideEdge = function(edge, isFilter) {
		if (edge.visible()) {
			edge.hide();
			edge.data().visible = false;
			var cls = isFilter ? "toggled-filter" : "toggled-hide";
			edge.addClass(cls);
		}
	};
}
 
function DijkstraManager(graphRef) {
	var _this = this;
	
	this.root = null;		// root node from which the paths will originate
	this.dest = null;		// the terminal or destination node
	this.isWaiting = false;	// whether this manager is waiting for destination node or not
	
	this.setRoot = function(node) { 
		if (typeof node.isNode == "function" && node.isNode() == true) {
			_this.root = node;
			_this.setWait(true);
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(0, "Root node " + /*(id='" + node.data("id") + "') */ "selected.  Left-click another node to show the shortest path between them.");
			}
		}
	};
	
	this.setDest = function(node) {
		if (typeof node.isNode == "function" && node.isNode() == true) {
			_this.dest = node;
			
			if (graphRef.owner.getProgressBar) {
				var pb = graphRef.owner.getProgressBar();
				if (pb) pb.updateProgress(1, "100%");
			}
		}
	};
	
	this.setWait = function(bool) {
		_this.isWaiting = (bool === true) ? true : false;
	};
	
	this.run = function(followDirection) {
		var eles = graphRef.gv.elements();
		var directed = (followDirection === true) ? true : false;
		// function that returns edge weight where this == current edge
		var getEdgeWeight = function() {
			var a = this.data("weight"); 
			if (typeof a == "undefined" || parseInt(a) <= 0) {
				console.log("Could not get valid weight from edge id='" + this.data().id + "'");
				a = "1";
			}
			//console.log("Dijkstra: weight is " + a); 
			return parseInt(a);
		};
		
		// the last boolean parameter of dijkstra() is a flag to only follow edge directions, yes/no
		var result = eles.dijkstra(_this.root, getEdgeWeight, directed);
		return {
			paths: result.pathTo(_this.dest),
			distance: result.distanceTo(_this.dest)
		};
	};
	
	this.clear = function() {
		_this.root = null;
		_this.dest = null;
		_this.isWaiting = false;
		graphRef.gv.elements().removeClass("on-path");
	};
}

function GraphGenerator(graphRef) {
	var _this = this;
	var _currentId = 0;
	var _parentNode = null;
	
	var _stateEnum = {
		ADD:		{value: false, name: "ADD"},
		CONNECT:	{value: false, name: "CONNECT"},
		MERGE:		{value: false, name: "MERGE"}
	};
	
	var _getCurrentState = function() {
		for (var state in _stateEnum) {
			if (_stateEnum.hasOwnProperty(state)) {
				if (_stateEnum[state].value === true)
					return _stateEnum[state].name;
			}
		}
		return null;
	};
	
	var _say = function(progressAmount, progressMessage) {
		if (graphRef.owner.getProgressBar) {
			var pb = graphRef.owner.getProgressBar();
			if (pb) pb.updateProgress(progressAmount, progressMessage);
		}
	};
	
	this.setParent = function(node) {
		if (typeof node.isNode == "function" && node.isNode() == true) {
			_parentNode = node;
		}
	};
	
	this.addNode = function(x, y) {
		// create node and set node's position to (x, y)
		var nodeJSON = {
			data: {
				id: "generatedNode_" + _currentId,
				idVal: "GeneratedNode_" + _currentId,
				idType: "GENERATED",
				name: "New Node",
				size: graphRef.CONSTANTS("nodeSize"),
				label: "New Node*",
				color: "gray",
				generated: true,
				attrs: [/* Populated via NodeEditor */]
			},
			group: "nodes",
			position: {x: x, y: y}
		};
		
		// create edge between new node and _parentNode
		var edgeJSON = {
			data: {
				id: "generatedEdge_" + _currentId,
				idVal: "GeneratedEdge_" + _currentId,
				idType: "GENERATED",
				color: graphRef.CONSTANTS("defaultEdge"),
				source: _parentNode.data("id"),
				target: "generatedNode_" + _currentId,
				lineStyle: "dashed",
				generated: true,
				count: 1,
				label: "",
				attrs: [/* Populated via NodeEditor */]
			},
			group: "edges"
		};
		
		// add node and edge json graphRef.gv
		graphRef.gv.add([nodeJSON, edgeJSON]);
		
		// increment the running id so we can have unique nodes/edges
		_currentId++;
	};
	
	this.connectNodes = function(targetNode) {
		if (_this.stateMatches("CONNECT") && typeof _parentNode !== "undefined") {
			// create edge between targetNode and _parentNode
			var edgeJSON = {
				data: {
					id: "generatedEdge_" + _currentId,
					idVal: "GeneratedEdge_" + _currentId,
					idType: "GENERATED",
					color: graphRef.CONSTANTS("defaultEdge"),
					source: _parentNode.data("id"),
					target: targetNode.data("id"),
					lineStyle: "dashed",
					generated: true,
					count: 1,
					label: "",
					attrs: [/* Populated via NodeEditor */]
				},
				group: "edges"
			};
			// add edge json to graphRef.gv
			graphRef.gv.add(edgeJSON);
			
			// increment the running id so we can have unique nodes/edges
			_currentId++;
		}
	};
	
	this.changeState = function(newState) {
		if (typeof newState !== "string") return;
		
		switch (newState.toUpperCase()) {
			case "ADD":
				_stateEnum.ADD.value = true;
				_stateEnum.CONNECT.value = false;
				_stateEnum.MERGE.value = false;
				break;
			case "CONNECT":
				_stateEnum.ADD.value = false;
				_stateEnum.CONNECT.value = true;
				_stateEnum.MERGE.value = false;
				break;
			case "MERGE":
				_stateEnum.ADD.value = false;
				_stateEnum.CONNECT.value = false;
				_stateEnum.MERGE.value = true;
				break;
			default:
				break;
		}
	};
	
	this.stateMatches = function(inState) {
		if (typeof inState !== "string") return false;
		return inState.toUpperCase() == _getCurrentState();
	};
	
	this.givePrompt = function() {
		switch (_getCurrentState()) {
			case "ADD":
				_say(0, "Click on graph whitespace to place new node there.");
				break;
			case "CONNECT":
				_say(0, "Click on another node to draw an edge.");
				break;
			case "MERGE":
				// TODO 
				break;
			default:
				_say(1, "100%");
				break;
		}
	};
	
	this.clear = function() {
		_root = null;
		_stateEnum.ADD.value = false;
		_stateEnum.CONNECT.value = false;
		_stateEnum.MERGE.value = false;
	};
}